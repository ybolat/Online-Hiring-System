package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.catalog.DevelopmentType;
import kz.edu.astanait.diplomawork.repository.catalog.DevelopmentTypeRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.DevelopmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DevelopmentTypeServiceImpl implements DevelopmentTypeService {

    private final DevelopmentTypeRepository developmentTypeRepository;

    @Autowired
    public DevelopmentTypeServiceImpl(DevelopmentTypeRepository developmentTypeRepository) {
        this.developmentTypeRepository = developmentTypeRepository;
    }

    @Override
    public Optional<DevelopmentType> getById(Long id) {
        return this.developmentTypeRepository.findById(id);
    }

    @Override
    public DevelopmentType getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Development Type", "id", id)));
    }
}
