package kz.edu.astanait.diplomawork.repository.catalog;

import kz.edu.astanait.diplomawork.model.catalog.AcademicDegree;
import kz.edu.astanait.diplomawork.model.catalog.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTypeRepository extends JpaRepository<ArticleType, Long> {


}
