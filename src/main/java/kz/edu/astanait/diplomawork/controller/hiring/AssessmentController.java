package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.AssessmentDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.AssessmentDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.AssessmentMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/assessment")
@PreAuthorize("hasRole('ROLE_COMMISSION')")
public class AssessmentController {

    private final AssessmentService assessmentService;

    @Autowired
    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping("/get/request/id/{id}")
    public ResponseEntity<List<AssessmentDtoResponse>> getAllByRequestId(@PathVariable Long id) {
        List<AssessmentDtoResponse> assessmentDtoResponseList = this.assessmentService.getAllByRequestId(id)
                .stream().map(AssessmentMapper::assessmentToDtoResponse).collect(Collectors.toList());
        return new ResponseEntity<>(assessmentDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody AssessmentDtoRequest assessmentDtoRequest) {
        this.assessmentService.create(assessmentDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
