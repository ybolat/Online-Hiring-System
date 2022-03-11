package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.model.catalog.Status;
import kz.edu.astanait.diplomawork.model.hiring.Certificate;

import java.util.List;
import java.util.Optional;

public interface StatusService {

    List<Status> getAll();

    Optional<Status> getById(Long id);

    Status getByIdThrowException(Long id);
}
