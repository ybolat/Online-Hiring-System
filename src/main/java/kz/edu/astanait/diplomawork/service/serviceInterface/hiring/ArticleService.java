package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.catalog.AcademicDegree;
import kz.edu.astanait.diplomawork.model.hiring.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllByUserId(Long id);
}
