package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.repository.catalog.DevelopmentTypeRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.DevelopmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevelopmentTypeServiceImpl implements DevelopmentTypeService {

    private final DevelopmentTypeRepository developmentTypeRepository;

    @Autowired
    public DevelopmentTypeServiceImpl(DevelopmentTypeRepository developmentTypeRepository) {
        this.developmentTypeRepository = developmentTypeRepository;
    }

}
