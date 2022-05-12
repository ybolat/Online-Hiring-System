package kz.edu.astanait.diplomawork.service.serviceImpl.statistic;

import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.*;
import kz.edu.astanait.diplomawork.service.serviceInterface.statistic.StatisticService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatisticServiceImpl implements StatisticService {

    private final UserService userService;
    private final ArticleService articleService;
    private final CertificateService certificateService;
    private final DevelopmentService developmentService;
    private final ProjectService projectService;
    private final PublicationsService publicationsService;
    private final RequestService requestService;

    @Autowired
    public StatisticServiceImpl(UserService userService, ArticleService articleService, CertificateService certificateService, DevelopmentService developmentService, ProjectService projectService, PublicationsService publicationsService, RequestService requestService) {
        this.userService = userService;
        this.articleService = articleService;
        this.certificateService = certificateService;
        this.developmentService = developmentService;
        this.projectService = projectService;
        this.publicationsService = publicationsService;
        this.requestService = requestService;
    }

}
