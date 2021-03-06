package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ProjectDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.InvalidData;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Project;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import kz.edu.astanait.diplomawork.repository.hiring.ProjectRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.ProjectTypeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ProjectService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserProfessionalInfoService userProfessionalInfoService;
    private final ProjectTypeService projectTypeService;
    private final UserService userService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserProfessionalInfoService userProfessionalInfoService, ProjectTypeService projectTypeService, UserService userService) {
        this.projectRepository = projectRepository;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.projectTypeService = projectTypeService;
        this.userService = userService;
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
    public List<Project> getMyProjects(Principal principal) {
        return this.projectRepository.findByUserEmail(principal.getName());
    }

    @Override
    public void create(ProjectDtoRequest projectDtoRequest, Principal principal) {
        Project project = new Project();

        User user = this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName());

        project.setUserProfessionalInfo(this.userProfessionalInfoService.getByUserIdThrowException(user.getId()));
        project.setStartedDate(projectDtoRequest.getStartedDate());
        project.setProjectName(projectDtoRequest.getProjectName());
        project.setFinishedDate(projectDtoRequest.getFinishedDate());
        project.setRole(projectDtoRequest.getRole());
        project.setSum(projectDtoRequest.getSum());
        project.setFund(projectDtoRequest.getFund());
        project.setProjectType(this.projectTypeService.getByIdThrowException(projectDtoRequest.getProjectTypeId()));

        try{
            this.projectRepository.save(project);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "creating", "project"));
        }
    }

    @Override
    public void update(ProjectDtoRequest projectDtoRequest, Long id) {
        Project project = this.getByIdThrowException(id);

        if (Strings.isNotBlank(projectDtoRequest.getProjectName())) project.setProjectName(projectDtoRequest.getProjectName());
        if (Objects.nonNull(projectDtoRequest.getStartedDate())) project.setStartedDate(projectDtoRequest.getStartedDate());
        if (Strings.isNotBlank(projectDtoRequest.getRole())) project.setRole(projectDtoRequest.getRole());
        if (Objects.nonNull(projectDtoRequest.getFinishedDate())) project.setFinishedDate(projectDtoRequest.getFinishedDate());
        if (Objects.nonNull(projectDtoRequest.getSum())) project.setSum(projectDtoRequest.getSum());
        if (Strings.isNotBlank(projectDtoRequest.getFund())) project.setFund(projectDtoRequest.getFund());
        if (Objects.nonNull(projectDtoRequest.getProjectTypeId())) project.setProjectType(this.projectTypeService.getByIdThrowException(projectDtoRequest.getProjectTypeId()));

        try{
            this.projectRepository.save(project);
        }catch (Exception e){
            log.error(e);
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
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "deleting", "project"));
        }
    }

    @Override
    public void createAll(List<ProjectDtoRequest> projectDtoRequestList, Principal principal){
        if (projectDtoRequestList.size() == 0) throw new InvalidData(ExceptionDescription.InvalidDataException);

        List<Project> projectList = new ArrayList<>();


        User user = this.userService.getByEmailThrowException(principal.getName());
        UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(user.getId());

        for(ProjectDtoRequest projectDtoRequest: projectDtoRequestList){
            Project project = new Project();

           if(Strings.isNotBlank(projectDtoRequest.getProjectName())) project.setProjectName(projectDtoRequest.getProjectName());
            project.setUserProfessionalInfo(userProfessionalInfo);
           if(Objects.nonNull(projectDtoRequest.getStartedDate())) project.setStartedDate(projectDtoRequest.getStartedDate());
           if(Objects.nonNull(projectDtoRequest.getFinishedDate())) project.setFinishedDate(projectDtoRequest.getFinishedDate());
           if(Objects.nonNull(projectDtoRequest.getRole())) project.setRole(projectDtoRequest.getRole());
           if(Objects.nonNull(projectDtoRequest.getSum())) project.setSum(projectDtoRequest.getSum());
           if(Strings.isNotBlank(projectDtoRequest.getFund())) project.setFund(projectDtoRequest.getFund());
           if(Objects.nonNull(projectDtoRequest.getProjectTypeId())) project.setProjectType(this.projectTypeService.getByIdThrowException(projectDtoRequest.getProjectTypeId()));

            projectList.add(project);
        }

        try{
            this.projectRepository.saveAll(projectList);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "projectServiceImpl"));
        }
    }
}
