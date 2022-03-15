package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findArticleByUserProfessionalInfoId(Long id);

//    @Query(nativeQuery = true, value = "select * from article order by article_name")
//    List<Article> findAllArticleOrderByArticleName();
//

    List<Article> findAllByUserProfessionalInfoIdOrderByArticleName(Long id);
}
