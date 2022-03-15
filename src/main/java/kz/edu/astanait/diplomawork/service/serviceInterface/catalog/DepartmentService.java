package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.model.catalog.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<Department> getAll();

    Optional<Department> getById(Long id);

    Department getByIdThrowException(Long id);
}
