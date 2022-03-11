package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Development;

import java.util.List;
import java.util.Optional;

public interface DevelopmentService {

    List<Development> getAllByUserId(Long id);

    Optional<Development> getById(Long id);

    Development getByIdThrowException(Long id);
}
