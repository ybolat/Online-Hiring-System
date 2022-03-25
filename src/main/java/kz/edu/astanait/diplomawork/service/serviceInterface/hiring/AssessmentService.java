package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.AssessmentDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Assessment;
import java.util.List;
import java.util.Optional;

public interface AssessmentService {

    List<Assessment> getAllByRequestId(Long id);

    Optional<Assessment> getById(Long id);

    Assessment getByIdThrowException(Long id);

    void create(AssessmentDtoRequest assessmentDtoRequest);
}
