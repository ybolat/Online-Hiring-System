package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.model.catalog.ProjectType;
import kz.edu.astanait.diplomawork.repository.catalog.ProjectTypeRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.ProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

    private final ProjectTypeRepository projectTypeRepository;

    @Autowired
    public ProjectTypeServiceImpl(ProjectTypeRepository projectTypeRepository) {
        this.projectTypeRepository = projectTypeRepository;
    }

    @Override
    public List<ProjectType> getAll() {
        return this.projectTypeRepository.findAll();
    }
}
