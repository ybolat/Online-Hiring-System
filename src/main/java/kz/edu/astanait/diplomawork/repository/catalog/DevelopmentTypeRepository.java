package kz.edu.astanait.diplomawork.repository.catalog;

import kz.edu.astanait.diplomawork.model.catalog.AcademicDegree;
import kz.edu.astanait.diplomawork.model.catalog.DevelopmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevelopmentTypeRepository extends JpaRepository<DevelopmentType, Long> {
}
