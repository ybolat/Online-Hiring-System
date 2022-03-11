package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.model.catalog.DevelopmentType;

import java.util.Optional;

public interface DevelopmentTypeService {

    Optional<DevelopmentType> getById(Long id);

    DevelopmentType getByIdThrowException(Long id);
}
