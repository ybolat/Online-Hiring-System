package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Request;

import java.util.List;
import java.util.Optional;

public interface RequestService {

    List<Request> getAllRequest();

    Optional<Request> getById(Long id);

    Request getByIdThrowException(Long id);
}
