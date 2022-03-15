package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.catalog.Position;
import kz.edu.astanait.diplomawork.repository.catalog.PositionRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Optional<Position> getById(Long id) {
        return this.positionRepository.findById(id);
    }

    @Override
    public Position getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Position", "id", id)));
    }
}
