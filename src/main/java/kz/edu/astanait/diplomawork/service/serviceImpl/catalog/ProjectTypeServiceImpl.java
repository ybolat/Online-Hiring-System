package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.catalog.ProjectType;
import kz.edu.astanait.diplomawork.repository.catalog.ProjectTypeRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.ProjectTypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
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

    @Override
    public Optional<ProjectType> getById(Long id) {
        return this.projectTypeRepository.findById(id);
    }

    @Override
    public ProjectType getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Project Type", "id", id)));
    }
}
