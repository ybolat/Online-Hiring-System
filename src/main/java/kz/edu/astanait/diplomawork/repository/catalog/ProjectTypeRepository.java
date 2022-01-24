package kz.edu.astanait.diplomawork.repository.catalog;
import kz.edu.astanait.diplomawork.model.catalog.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long> {
}
