package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.AssessmentDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.AssessmentMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.AssessmentService;
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
@RequestMapping("/api/v1/hiring/assessment")
public class AssessmentController {

    private final AssessmentService assessmentService;

    @Autowired
    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping("/get/request/id/{id}")
    public ResponseEntity<List<AssessmentDtoResponse>> getAllByRequestId(@PathVariable Long id) {
        List<AssessmentDtoResponse> assessmentDtoResponseList = assessmentService.getAllByRequestId(id)
                .stream().map(AssessmentMapper::assessmentToDtoResponse).collect(Collectors.toList());
        return new ResponseEntity<>(assessmentDtoResponseList, HttpStatus.OK);
    }
}
