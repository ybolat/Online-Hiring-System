package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ArticleDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.InvalidData;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Article;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
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
import java.util.ArrayList;
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
    public List<Article> getMyArticles(Principal principal) {
        return this.articleRepository.findByUserEmail(principal.getName());
    }

    @Override
    public List<Article> getAllByUserProfessionalInfoIdOrderByArticleName(Long id) {
        return this.articleRepository.findAllByUserProfessionalInfoIdOrderByArticleName(id);
    }

    @Override
    public Integer getAverageNumOfArticles(LocalDateTime dateTime, Long id) {
        List<User> userList = this.userService.getAllAcceptedUsers(dateTime, id);
        double sum = 0;

        for (int i = 0; i < userList.size(); i++) {
            List<Article> articleList = this.articleRepository.findByUserId(id);
            sum += articleList.size();
        }

        return (int) sum / userList.size();
    }

    @Override
    public void create(ArticleDtoRequest articleDtoRequest, Principal principal) {
        Article article = new Article();

        User user = this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName());

        article.setArticleName(articleDtoRequest.getTitle());
        article.setApa(articleDtoRequest.getApa());
        article.setDoi(articleDtoRequest.getDoi());
        article.setAuthors(articleDtoRequest.getAuthors());
        article.setSource(articleDtoRequest.getSource());
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
        if (Strings.isNotBlank(articleDtoRequest.getAuthors())) article.setAuthors(articleDtoRequest.getAuthors());
        if (Strings.isNotBlank(articleDtoRequest.getSource())) article.setSource(articleDtoRequest.getSource());


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

    @Override
    public void createAll(List<ArticleDtoRequest> articleDtoRequestList, Principal principal) {
        if (articleDtoRequestList.size() == 0) throw new InvalidData(ExceptionDescription.InvalidDataException);
        List<Article> articleList = new ArrayList<>();

        User user = this.userService.getByEmailThrowException(principal.getName());
        UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(user.getId());

        for (ArticleDtoRequest articleDtoRequest : articleDtoRequestList) {
            Article article = new Article();

            if (Strings.isNotBlank(articleDtoRequest.getTitle())) article.setArticleName(articleDtoRequest.getTitle());
            if (Strings.isNotBlank(articleDtoRequest.getApa())) article.setApa(articleDtoRequest.getApa());
            if(Strings.isNotBlank(articleDtoRequest.getDoi())) article.setDoi(articleDtoRequest.getDoi());
             article.setUserProfessionalInfo(userProfessionalInfo);
            if(Strings.isNotBlank(articleDtoRequest.getAuthors())) article.setAuthors(articleDtoRequest.getAuthors());
            if(Strings.isNotBlank(articleDtoRequest.getSource())) article.setSource(articleDtoRequest.getSource());
            if(Objects.nonNull(articleDtoRequest.getArticleTypeId())) article.setArticleType(this.articleTypeService.getByIdThrowException(articleDtoRequest.getArticleTypeId()));

            articleList.add(article);
        }

        try {
            this.articleRepository.saveAll(articleList);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating list of", "articles"));
        }
    }
}
