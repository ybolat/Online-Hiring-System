package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findArticleByUserProfessionalInfoId(Long id);

    List<Article> findAllByUserProfessionalInfoIdOrderByArticleName(Long id);

//    @Query(nativeQuery = true, value = "select * from article inner join user_professional_info on " +
//            "article.user_professional_info_id = user_professional_info.id where user_professional_info.user_id = :id")
//    List<Article> findByUserId(Long Id);

}
