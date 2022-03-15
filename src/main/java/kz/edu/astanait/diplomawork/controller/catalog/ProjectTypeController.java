package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.ProjectTypeDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.ProjectTypeMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.ProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/project-type")
public class ProjectTypeController {

    private final ProjectTypeService projectTypeService;

    @Autowired
    public ProjectTypeController(ProjectTypeService projectTypeService) {
        this.projectTypeService = projectTypeService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProjectTypeDtoResponse>> getAll() {
        List<ProjectTypeDtoResponse> projectDtoResponseList = this.projectTypeService.getAll()
                .stream().map(ProjectTypeMapper::projectTypeToDto).collect(Collectors.toList());
        return new ResponseEntity<>(projectDtoResponseList, HttpStatus.OK);
    }
}
