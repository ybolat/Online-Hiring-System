package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.repository.catalog.PositionRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
}
