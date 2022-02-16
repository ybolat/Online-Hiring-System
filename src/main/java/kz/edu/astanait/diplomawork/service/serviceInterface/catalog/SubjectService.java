package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.model.catalog.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    List<Subject> getAll();

    Optional<Subject> getById(Long id);

    Subject getByIdThrowException(Long id);
}
