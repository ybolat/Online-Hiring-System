package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Article;
import kz.edu.astanait.diplomawork.model.hiring.Development;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevelopmentRepository extends JpaRepository<Development, Long> {
    List<Development> findAllByUserProfessionalInfoId(Long id);

    @Query(nativeQuery = true, value = "select * from development inner join user_professional_info on " +
            "development.user_professional_info_id = user_professional_info.id inner join users on user_professional_info.user_id = users.id where users.email=:email")
    List<Development> findByUserEmail(String email);
}
