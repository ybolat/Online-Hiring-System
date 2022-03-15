package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.hiring.Project;
import kz.edu.astanait.diplomawork.repository.hiring.ProjectRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllByUserProfessionalInfoId(Long id) {
        return this.projectRepository.findAllByUserProfessionalInfoId(id);
    }

    @Override
    public Optional<Project> getById(Long id) {
        return this.projectRepository.findById(id);
    }

    @Override
    public Project getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Project", "id", id)));
    }
}
