package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ArticleDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Article;
import kz.edu.astanait.diplomawork.repository.hiring.ArticleRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.ArticleTypeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ArticleService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final UserProfessionalInfoService userProfessionalInfoService;
    private final ArticleTypeService articleTypeService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserProfessionalInfoService userProfessionalInfoService, ArticleTypeService articleTypeService) {
        this.articleRepository = articleRepository;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.articleTypeService = articleTypeService;
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
    public void create(ArticleDtoRequest articleDtoRequest) {
        Article article = new Article();

        article.setApa(articleDtoRequest.getApa());
        article.setDoi(articleDtoRequest.getDoi());
        article.setUserProfessionalInfo(this.userProfessionalInfoService.getByIdThrowException(articleDtoRequest.getUserProfessionalInfoId()));
        article.setArticleType(this.articleTypeService.getByIdThrowException(articleDtoRequest.getArticleTypeId()));

        try{
            this.articleRepository.save(article);
        }catch (Exception e){
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
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "deleting", "article"));
        }
    }

//    @Override
//    public List<Article> getAllOrderByArticleName() {
//        return this.articleRepository.findAllArticleOrderByArticleName();
//    }

    @Override
    public List<Article> getAllByUserProfessionalInfoIdOrderByArticleName(Long id) {
        return this.articleRepository.findAllByUserProfessionalInfoIdOrderByArticleName(id);
    }
}
