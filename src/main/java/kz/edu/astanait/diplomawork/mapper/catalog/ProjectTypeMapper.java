package kz.edu.astanait.diplomawork.mapper.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.ProjectTypeDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.ProjectType;
import org.apache.logging.log4j.util.Strings;

public class ProjectTypeMapper {

    public static ProjectTypeDtoResponse projectTypeToDto(ProjectType projectType) {
        ProjectTypeDtoResponse projectTypeDtoResponse = new ProjectTypeDtoResponse();
        projectTypeDtoResponse.setId(projectType.getId());
        if(Strings.isNotBlank(projectType.getTitle())) projectTypeDtoResponse.setTitle(projectType.getTitle());
        return projectTypeDtoResponse;
    }
}
