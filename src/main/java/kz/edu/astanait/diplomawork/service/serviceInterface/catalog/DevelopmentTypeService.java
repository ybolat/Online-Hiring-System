package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.model.catalog.DevelopmentType;
import java.util.List;
import java.util.Optional;

public interface DevelopmentTypeService {

    List<DevelopmentType> getAll();

    Optional<DevelopmentType> getById(Long id);

    DevelopmentType getByIdThrowException(Long id);
}
