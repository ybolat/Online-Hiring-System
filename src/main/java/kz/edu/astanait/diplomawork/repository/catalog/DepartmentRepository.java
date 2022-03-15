package kz.edu.astanait.diplomawork.repository.catalog;

import kz.edu.astanait.diplomawork.model.catalog.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
