package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Article;
import kz.edu.astanait.diplomawork.model.hiring.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findAllByUserProfessionalInfoId(Long id);

    @Query(nativeQuery = true, value = "select * from certificate inner join user_professional_info on " +
            "certificate.user_professional_info_id = user_professional_info.id inner join users on user_professional_info.user_id = users.id where users.email=:email")
    List<Certificate> findByUserEmail(String email);
}
