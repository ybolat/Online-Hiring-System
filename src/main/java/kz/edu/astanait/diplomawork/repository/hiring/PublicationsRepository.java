package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Publications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PublicationsRepository extends JpaRepository<Publications, Long> {
    List<Publications> findAllByUserProfessionalInfoId(Long id);
}
