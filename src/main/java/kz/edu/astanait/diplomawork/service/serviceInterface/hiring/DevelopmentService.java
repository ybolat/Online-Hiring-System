package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.DevelopmentDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Development;
import java.util.List;
import java.util.Optional;

public interface DevelopmentService {

    List<Development> getAllByUserProfessionalInfoId(Long id);

    Optional<Development> getById(Long id);

    Development getByIdThrowException(Long id);

    void create(DevelopmentDtoRequest developmentDtoRequest);

    void update(DevelopmentDtoRequest developmentDtoRequest, Long id);

    void delete(Long id);
}
