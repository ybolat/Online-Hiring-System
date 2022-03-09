package kz.edu.astanait.diplomawork.repository.catalog;

import kz.edu.astanait.diplomawork.model.catalog.AcademicDegree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<AcademicDegree, Long> {
}