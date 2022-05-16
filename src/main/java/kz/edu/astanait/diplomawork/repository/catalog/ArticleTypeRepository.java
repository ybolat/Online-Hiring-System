package kz.edu.astanait.diplomawork.repository.catalog;

import kz.edu.astanait.diplomawork.model.catalog.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleTypeRepository extends JpaRepository<ArticleType, Long> {

    Optional<ArticleType> findByTitle(String title);
}
