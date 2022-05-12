package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ArticleDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Article;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.repository.hiring.ArticleRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.ArticleTypeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ArticleService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final UserProfessionalInfoService userProfessionalInfoService;
    private final ArticleTypeService articleTypeService;
    private final UserService userService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserProfessionalInfoService userProfessionalInfoService, ArticleTypeService articleTypeService, UserService userService) {
        this.articleRepository = articleRepository;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.articleTypeService = articleTypeService;
        this.userService = userService;
    }

    @Override
    public List<Article> getAllByUserProfessionalInfoId(Long id) {
        return this.articleRepository.findArticleByUserProfessionalInfoId(id);
    }

    @Override
    public Optional<Article> getById(Long id) {
        return this.articleRepository.findById(id);
    }

    @Override
    public Article getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Article", "id", id)));
    }

    @Override
    public List<Article> getAllByUserProfessionalInfoIdOrderByArticleName(Long id) {
        return this.articleRepository.findAllByUserProfessionalInfoIdOrderByArticleName(id);
    }

    public void helloWorld() {
        System.out.println("hello world");
    }

//    @Override
//    public Integer getAverageNumOfArticles(LocalDateTime dateTime, Long id) {
//        List<User> userList = this.userService.getAllAcceptedUsers(dateTime, id);
//        double sum = 0;
//
//        for (int i = 0; i < userList.size(); i++) {
//            List<Article> articleList = this.articleRepository.findByUserId(id);
//            sum += articleList.size();
//        }
//
//        return (int) sum / userList.size();
//    }

    @Override
    public void create(ArticleDtoRequest articleDtoRequest, Principal principal) {
        Article article = new Article();

        User user = this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName());

        article.setApa(articleDtoRequest.getApa());
        article.setDoi(articleDtoRequest.getDoi());
        article.setUserProfessionalInfo(this.userProfessionalInfoService.getByUserIdThrowException(user.getId()));
        article.setArticleType(this.articleTypeService.getByIdThrowException(articleDtoRequest.getArticleTypeId()));

        try{
            this.articleRepository.save(article);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "creating", "article"));
        }
    }

    @Override
    public void update(ArticleDtoRequest articleDtoRequest, Long id) {
        Article article = this.getByIdThrowException(id);

        if (Strings.isNotBlank(articleDtoRequest.getApa())) article.setApa(articleDtoRequest.getApa());
        if (Strings.isNotBlank(articleDtoRequest.getDoi())) article.setDoi(articleDtoRequest.getDoi());
        if (Objects.nonNull(articleDtoRequest.getArticleTypeId())) article.setArticleType(this.articleTypeService.getByIdThrowException(articleDtoRequest.getArticleTypeId()));

        try{
            this.articleRepository.save(article);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "updating", "article"));
        }
    }

    @Override
    public void delete(Long id) {
        Article article = this.getByIdThrowException(id);

        try{
            this.articleRepository.delete(article);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "deleting", "article"));
        }
    }
}
