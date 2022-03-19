package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ProjectDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Project;
import kz.edu.astanait.diplomawork.repository.hiring.ProjectRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.ProjectTypeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ProjectService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserProfessionalInfoService userProfessionalInfoService;
    private final ProjectTypeService projectTypeService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserProfessionalInfoService userProfessionalInfoService, ProjectTypeService projectTypeService) {
        this.projectRepository = projectRepository;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.projectTypeService = projectTypeService;
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

    @Override
    public void create(ProjectDtoRequest projectDtoRequest) {

        Project project = new Project();

        project.setUserProfessionalInfo(this.userProfessionalInfoService.getByIdThrowException(projectDtoRequest.getUserProfessionalInfoId()));
        project.setStartedDate(projectDtoRequest.getStartedDate());
        project.setFinishedDate(projectDtoRequest.getFinishedDate());
        project.setRole(projectDtoRequest.getRole());
        project.setSum(projectDtoRequest.getSum());
        project.setFund(projectDtoRequest.getFund());
        project.setProjectType(this.projectTypeService.getByIdThrowException(projectDtoRequest.getProjectTypeId()));

        try{
            this.projectRepository.save(project);
        }catch (Exception e){
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "creating", "project"));
        }
    }

    @Override
    public void update(ProjectDtoRequest projectDtoRequest, Long id) {
        Project project = this.getByIdThrowException(id);

        if(Objects.nonNull(projectDtoRequest.getStartedDate())) project.setStartedDate(project.getStartedDate());
        if(Objects.nonNull(projectDtoRequest.getFinishedDate())) project.setFinishedDate(project.getFinishedDate());
        if(Strings.isNotBlank(projectDtoRequest.getRole())) project.setRole(project.getRole());
        if(Objects.nonNull(projectDtoRequest.getSum())) project.setSum(project.getSum());
        if(Strings.isNotBlank(projectDtoRequest.getFund())) project.setFund(project.getFund());
        if(Objects.nonNull(projectDtoRequest.getProjectTypeId())) project.setProjectType(project.getProjectType());

        try{
            this.projectRepository.save(project);
        }catch (Exception e){
            throw new RepositoryException(String
            .format(ExceptionDescription.RepositoryException, "updating", "project"));
        }
    }

    @Override
    public void delete(Long id) {
        Project project = this.getByIdThrowException(id);

        try{
            this.projectRepository.delete(project);
        }catch (Exception e){
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "deleting", "project"));
        }
    }
}
