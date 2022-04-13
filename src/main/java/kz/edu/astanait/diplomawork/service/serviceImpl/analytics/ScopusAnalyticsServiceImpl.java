package kz.edu.astanait.diplomawork.service.serviceImpl.analytics;

import kz.edu.astanait.diplomawork.model.hiring.Article;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import kz.edu.astanait.diplomawork.service.serviceInterface.analytics.ScopusAnalyticsService;
import kz.edu.astanait.diplomawork.service.serviceInterface.feign.ScopusService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ArticleService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScopusAnalyticsServiceImpl implements ScopusAnalyticsService {

    private final UserProfessionalInfoService userProfessionalInfoService;
    private final ArticleService articleService;
    private final ScopusService scopusService;

    @Autowired
    public ScopusAnalyticsServiceImpl(UserProfessionalInfoService userProfessionalInfoService, ArticleService articleService, ScopusService scopusService) {
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.articleService = articleService;
        this.scopusService = scopusService;
    }

    @Override
    public int refineArticlesByScopusId(Long userId) {
        UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(userId);
        List<Article> articles = this.articleService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());
        int counter = 0;

        if (articles.isEmpty()) return -1;

        articles = articles.stream().filter(v ->
             v.getArticleType().getTitle().equals("Scopus")
        ).collect(Collectors.toList());

        for (Article article : articles) {
            if (this.scopusService.searchScopusId(article.getDoi()).equals(userProfessionalInfo.getScopus())) {
                counter++;
            }
        }
        return counter;
    }
}
