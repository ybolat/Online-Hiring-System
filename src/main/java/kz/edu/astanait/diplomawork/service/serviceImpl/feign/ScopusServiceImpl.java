package kz.edu.astanait.diplomawork.service.serviceImpl.feign;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ArticleDtoRequest;
import kz.edu.astanait.diplomawork.feignClient.ScopusClient;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.ArticleTypeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.feign.ScopusService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ArticleService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ScopusServiceImpl implements ScopusService {

    private final ScopusClient scopusClient;

    private final UserProfessionalInfoService userProfessionalInfoService;
    private final ArticleService articleService;
    private final ArticleTypeService articleTypeService;

    @Autowired
    public ScopusServiceImpl(ScopusClient scopusClient, UserProfessionalInfoService userProfessionalInfoService, ArticleService articleService, ArticleTypeService articleTypeService) {
        this.scopusClient = scopusClient;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.articleService = articleService;
        this.articleTypeService = articleTypeService;
    }

    @Override
    public boolean getAuthorInformation(Principal principal) {
        UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(
                this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName()).getId());

        HashMap<String, String> result = this.scopusClient.getAuthorInformation(userProfessionalInfo.getScopus());
        AtomicInteger i = new AtomicInteger();
        result.forEach((k,v) -> {
            int index = k.indexOf(",");
            String lastname = k.substring(0, index);
            String name = k.substring(index + 2);

            if (userProfessionalInfo.getUser().getName().equals(name) && userProfessionalInfo.getUser().getLastname().equals(lastname)) {
                if (userProfessionalInfo.getOrcid().equals(v)) {
                    i.set(1);
                }
            }
        });
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(1);
        return i.equals(atomicInteger);
    }

    @Override
    public void getScopusInformation(Principal principal) {
        UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(
                this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName()).getId());

        HashMap<String, String> result = this.scopusClient.getArticleInformation(userProfessionalInfo.getScopus());
        result.forEach((k,v) -> {
            int idx1 = v.indexOf("$");
            int idx2 = v.indexOf("<");
            int idx3 = v.indexOf(">");
            int idx4 = v.indexOf("{");

            String title = v.substring(0,idx1);
            String articleType = v.substring(idx1, idx2);
            String doi = v.substring(idx2, idx3);
            String documentAuthors = v.substring(idx3, idx4);
            String source = v.substring(idx4);

            ArticleDtoRequest articleDtoRequest = new ArticleDtoRequest();

            articleDtoRequest.setArticleTypeId(this.articleTypeService.getByNameThrowException(articleType).getId());
            articleDtoRequest.setDoi(doi);
            articleDtoRequest.setTitle(title);
            articleDtoRequest.setAuthors(documentAuthors);
            articleDtoRequest.setSource(source);

            this.articleService.create(articleDtoRequest, principal);
        });
    }
}
