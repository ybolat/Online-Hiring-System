package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.model.catalog.AcademicTitle;

import java.util.List;
import java.util.Optional;

public interface AcademicTitleService {

    List<AcademicTitle> getAll();

    Optional<AcademicTitle> getById(Long id);

    AcademicTitle getByIdThrowException(Long id);
}
