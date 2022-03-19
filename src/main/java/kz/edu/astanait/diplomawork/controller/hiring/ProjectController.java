package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.ProjectDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.ProjectMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("get/user-professional-info/id/{id}")
    public ResponseEntity<List<ProjectDtoResponse>> getAllByUserProfessionalInfoId(@PathVariable(name = "id") Long id) {
        List<ProjectDtoResponse> projectDtoResponseList =
                this.projectService.getAllByUserProfessionalInfoId(id).stream().map(ProjectMapper::projectToDto).collect(Collectors.toList());
        return new ResponseEntity<>(projectDtoResponseList, HttpStatus.OK);
    }
}
