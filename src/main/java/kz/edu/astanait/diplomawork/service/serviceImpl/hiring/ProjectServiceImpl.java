package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Project;
import kz.edu.astanait.diplomawork.repository.hiring.ProjectRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllByUserId(Long id) {
        return projectRepository.findAllByUserId(id);
    }
}
