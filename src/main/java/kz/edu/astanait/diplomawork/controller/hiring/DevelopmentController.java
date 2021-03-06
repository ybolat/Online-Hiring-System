package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.DevelopmentDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.DevelopmentDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.hiring.DevelopmentMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DevelopmentService;
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
@RequestMapping("/api/v1/hiring/development")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
public class DevelopmentController extends ExceptionHandling {

    private final DevelopmentService developmentService;

    @Autowired
    public DevelopmentController(DevelopmentService developmentService) {
        this.developmentService = developmentService;
    }

    @GetMapping("/get/user-professional-info/id/{id}")
    public ResponseEntity<List<DevelopmentDtoResponse>> getAllByUserProfessionalInfoId(@PathVariable(name = "id") Long id) {
        List<DevelopmentDtoResponse> developmentDtoResponseList =
                this.developmentService.getAllByUserProfessionalInfoId(id).stream().map(DevelopmentMapper::developmentToDto).collect(Collectors.toList());
        return new ResponseEntity<>(developmentDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get/my-development")
    public ResponseEntity<List<DevelopmentDtoResponse>> getMyDevelopment(Principal principal) {
        List<DevelopmentDtoResponse> developmentDtoResponseList = this.developmentService.getMyDevelopment(principal)
                .stream().map(DevelopmentMapper::developmentToDto).collect(Collectors.toList());
        return new ResponseEntity<>(developmentDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody DevelopmentDtoRequest developmentDtoRequest, Principal principal){
        this.developmentService.create(developmentDtoRequest, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody DevelopmentDtoRequest developmentDtoRequest,
                                             @PathVariable(name = "id") Long id){
        this.developmentService.update(developmentDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.developmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create/all")
    public ResponseEntity<HttpStatus> createAll(@RequestBody List<DevelopmentDtoRequest> developmentDtoRequestList, Principal principal) {
        this.developmentService.createAll(developmentDtoRequestList, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
