package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.ProjectDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.UserMapper;
import kz.edu.astanait.diplomawork.mapper.catalog.ProjectTypeMapper;
import kz.edu.astanait.diplomawork.mapper.user.UserProfessionalInfoMapper;
import kz.edu.astanait.diplomawork.model.hiring.Project;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class ProjectMapper {

    public static ProjectDtoResponse projectToDto(Project project) {
        ProjectDtoResponse projectDtoResponse = new ProjectDtoResponse();
        projectDtoResponse.setId(project.getId());
        if(Objects.nonNull(project.getUserProfessionalInfo())) projectDtoResponse.setUserProfessionalInfoDtoResponse(UserProfessionalInfoMapper.userProfessionalInfoToDto(project.getUserProfessionalInfo()));
        if(Objects.nonNull(project.getStartedDate())) projectDtoResponse.setStartedDate(project.getStartedDate());
        if(Objects.nonNull(project.getFinishedDate())) projectDtoResponse.setFinishedDate(project.getFinishedDate());
        if(Objects.nonNull(project.getSum())) projectDtoResponse.setSum(project.getSum());
        if(Strings.isNotBlank(project.getFund())) projectDtoResponse.setFund(project.getFund());
        if(Objects.nonNull(project.getProjectType())) projectDtoResponse.setProjectType(ProjectTypeMapper.projectTypeToDto(project.getProjectType()));
        return projectDtoResponse;
    }
}
