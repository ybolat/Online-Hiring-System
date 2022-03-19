package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ProjectDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> getAllByUserProfessionalInfoId(Long id);

    Optional<Project> getById(Long id);

    Project getByIdThrowException(Long id);

    void create(ProjectDtoRequest projectDtoRequest);

    void update(ProjectDtoRequest projectDtoRequest, Long id);

    void delete(Long id);
}
