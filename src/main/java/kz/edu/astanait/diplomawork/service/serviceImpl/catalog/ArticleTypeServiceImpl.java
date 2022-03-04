package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.catalog.ArticleType;
import kz.edu.astanait.diplomawork.repository.catalog.ArticleTypeRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {

    private final ArticleTypeRepository articleRepository;

    @Autowired
    public ArticleTypeServiceImpl(ArticleTypeRepository articleTypeRepository) {
        this.articleRepository = articleTypeRepository;
    }

    @Override
    public List<ArticleType> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<ArticleType> getById(Long id) {
        return this.articleRepository.findById(id);
    }

    @Override
    public ArticleType getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Article type", "id", id)));
    }
}
