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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    public AtomicReference<Boolean> getAuthorInformation(Principal principal) {
        UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(
                this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName()).getId());

        HashMap<String, String> result = this.scopusClient.getAuthorInformation(userProfessionalInfo.getScopus());
        AtomicReference<Boolean> bool = new AtomicReference<>(false);

        result.forEach((k,v) -> {
            int index = k.indexOf(",");
            String lastname = k.substring(0, index);
            String name = k.substring(index + 2);

            if (userProfessionalInfo.getUser().getName().equalsIgnoreCase(name) && userProfessionalInfo.getUser().getLastname().equalsIgnoreCase(lastname)) {
                if (userProfessionalInfo.getOrcid().equals(v)) {
                    bool.set(true);
                }
            }
        });
        return bool;
    }

    @Override
    public Boolean getScopusInformation(Principal principal) {
        AtomicReference<Boolean> check = this.getAuthorInformation(principal);

        if (!check.get()){
            return false;
        }

        UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(
                this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName()).getId());

        HashMap<Integer, String>result = this.scopusClient.getArticleInformation(userProfessionalInfo.getScopus());
        List<ArticleDtoRequest> articleDtoRequestList = new ArrayList<>();

        result.forEach((k,v) -> {
            System.out.println(k);
            int idx1 = v.indexOf("$");
            int idx2 = v.indexOf("<");
            int idx3 = v.indexOf(">");
            int idx4 = v.indexOf("{");

            ArticleDtoRequest articleDtoRequest = new ArticleDtoRequest();

            articleDtoRequest.setTitle(v.substring(0,idx1));
            articleDtoRequest.setArticleTypeId(this.articleTypeService.getByNameThrowException(v.substring(idx1 + 1, idx2)).getId());
            articleDtoRequest.setDoi(v.substring(idx2 + 1, idx3));
            articleDtoRequest.setAuthors(v.substring(idx3 + 1, idx4));
            articleDtoRequest.setSource(v.substring(idx4 + 1));

            articleDtoRequestList.add(articleDtoRequest);

        });
        this.articleService.createAll(articleDtoRequestList, principal);
        return true;
    }
}
