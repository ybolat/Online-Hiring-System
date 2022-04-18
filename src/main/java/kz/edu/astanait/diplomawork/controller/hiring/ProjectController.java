package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.ProjectDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.ProjectDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.hiring.ProjectMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/project")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
public class ProjectController extends ExceptionHandling {

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

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody ProjectDtoRequest projectDtoRequest, Principal principal) {
        this.projectService.create(projectDtoRequest, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody ProjectDtoRequest projectDtoRequest,
                                             @PathVariable(name = "id") Long id) {
        this.projectService.update(projectDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
