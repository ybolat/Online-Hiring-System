package kz.edu.astanait.diplomawork.service.serviceImpl.feign;

import kz.edu.astanait.diplomawork.feignClient.ScopusClient;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import kz.edu.astanait.diplomawork.service.serviceInterface.feign.ScopusService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ArticleService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ScopusServiceImpl implements ScopusService {

    private final ScopusClient scopusClient;

    private final UserProfessionalInfoService userProfessionalInfoService;
    private final ArticleService articleService;

    @Autowired
    public ScopusServiceImpl(ScopusClient scopusClient, UserProfessionalInfoService userProfessionalInfoService, ArticleService articleService) {
        this.scopusClient = scopusClient;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.articleService = articleService;
    }

    @Override
    public void getScopusInformation(Long id) {
        UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(id);
        HashMap<String, List<String>> result = this.scopusClient.searchScopus(userProfessionalInfo.getScopus());
        result.forEach((k,v) -> {
            if(k.equals("author")) {
                int index = v.indexOf(",");
                String surname = v.get(0).substring(0, index);
                String name = v.get(0).substring(index + 2);
                if (surname.equals(userProfessionalInfo.getUser().getLastname()) && name.equals(userProfessionalInfo.getUser().getName())) {
                    System.out.println("everything is Ok");
                }
            }
            if (k.equals("orcid")) {
                userProfessionalInfo.setOrcid(v.get(0).substring(18));
            }
            while(true) {
                int i = 1;
                HashMap<HashMap<List<String>, List<String>>, HashMap<List<String>, List<String>>> hashMap = new HashMap<>();
                List<String> titles = new ArrayList<>();
                List<String> articleTypes = new ArrayList<>();
                List<String> publishers = new ArrayList<>();
                List<String> documentAuthors = new ArrayList<>();
                HashMap<List<String>, List<String >> hashMapKey = new HashMap<>();
                HashMap<List<String>, List<String >> hashMapValue = new HashMap<>();
                if (k.equals("titles " + i)) {
                    titles = v;
                }else if (k.equals("articleType " + i)) {
                    articleTypes = v;
                    hashMapKey.put(articleTypes, titles);
                }else if (k.equals("publisher " + 1)) {
                    publishers = v;
                }else if (k.equals("documentAuthors " + i)) {
                    documentAuthors = v;
                    hashMapValue.put(publishers, documentAuthors);
                    hashMap.put(hashMapKey, hashMapValue);
                }else {
                    break;
                }
            }
        });
    }
}
