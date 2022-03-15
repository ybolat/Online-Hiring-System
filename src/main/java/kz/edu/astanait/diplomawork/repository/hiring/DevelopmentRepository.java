package kz.edu.astanait.diplomawork.repository.hiring;
import kz.edu.astanait.diplomawork.model.hiring.Development;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevelopmentRepository extends JpaRepository<Development, Long> {

    List<Development> findAllByUserProfessionalInfoId(Long id);
}
