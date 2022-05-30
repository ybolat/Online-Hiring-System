package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {
    List<Syllabus> findAllByUserProfessionalInfoId(Long id);

    @Query(nativeQuery = true, value = "select * from syllabus inner join user_professional_info on " +
            "syllabus.user_professional_info_id = user_professional_info.id inner join users on user_professional_info.user_id = users.id where users.email=:email")
    List<Syllabus> findByUserEmail(String email);
}
