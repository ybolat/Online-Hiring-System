package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.model.catalog.Position;

import java.util.Optional;

public interface PositionService {

    Optional<Position> getById(Long id);

    Position getByIdThrowException(Long id);
}
