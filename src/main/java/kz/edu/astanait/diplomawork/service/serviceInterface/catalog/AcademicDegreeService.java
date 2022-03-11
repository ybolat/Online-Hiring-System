package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.model.catalog.AcademicDegree;
import kz.edu.astanait.diplomawork.model.hiring.Certificate;

import java.util.List;
import java.util.Optional;

public interface AcademicDegreeService {

    List<AcademicDegree> getAll();

    Optional<AcademicDegree> getById(Long id);

    AcademicDegree getByIdThrowException(Long id);
}
