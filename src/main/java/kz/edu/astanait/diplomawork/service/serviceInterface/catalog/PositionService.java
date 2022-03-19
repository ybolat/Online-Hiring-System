package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.model.catalog.Position;
import java.util.List;
import java.util.Optional;

public interface PositionService {

    List<Position> getAll();

    Optional<Position> getById(Long id);

    Position getByIdThrowException(Long id);
}
