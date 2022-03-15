package kz.edu.astanait.diplomawork.repository.hiring;
import kz.edu.astanait.diplomawork.model.hiring.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByUserProfessionalInfoId(Long id);
}
