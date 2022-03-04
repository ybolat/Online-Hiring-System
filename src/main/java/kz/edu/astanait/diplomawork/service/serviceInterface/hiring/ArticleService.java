package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ArticleDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getAllByUserId(Long id);

    Optional<Article> getById(Long id);

    Article getByIdThrowException(Long id);

    void create(ArticleDtoRequest articleDtoRequest);

    void update(ArticleDtoRequest articleDtoRequest, Long id);

    void delete(Long id);
}
