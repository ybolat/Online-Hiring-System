package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.model.catalog.ArticleType;

import java.util.List;
import java.util.Optional;

public interface ArticleTypeService {

    List<ArticleType> getAll();

    Optional<ArticleType> getById(Long id);

    ArticleType getByIdThrowException(Long id);
}
