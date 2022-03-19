package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.model.catalog.ProjectType;
import java.util.List;
import java.util.Optional;

public interface ProjectTypeService {

    List<ProjectType> getAll();

    Optional<ProjectType> getById(Long id);

    ProjectType getByIdThrowException(Long id);
}
