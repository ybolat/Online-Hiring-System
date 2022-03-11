package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> getAllByUserId(Long id);

    Optional<Project> getById(Long id);

    Project getByIdThrowException(Long id);
}
