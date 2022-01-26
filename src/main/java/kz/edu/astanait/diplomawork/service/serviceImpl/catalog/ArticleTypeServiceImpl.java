package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.model.catalog.ArticleType;
import kz.edu.astanait.diplomawork.repository.catalog.ArticleTypeRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
