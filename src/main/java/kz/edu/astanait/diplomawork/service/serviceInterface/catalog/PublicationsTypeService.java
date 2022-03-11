package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;
import kz.edu.astanait.diplomawork.model.catalog.PublicationsType;

import java.util.List;
import java.util.Optional;

public interface PublicationsTypeService {

    List<PublicationsType> getAll();

    Optional<PublicationsType> getById(Long id);

    PublicationsType getByIdThrowException(Long id);
}
