package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.SubjectDtoResponse;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/get-all-subject")
    public ResponseEntity<List<SubjectDtoResponse>> getAllSubject() {
        return new ResponseEntity<>(subjectService.getAllSubjectDto(), HttpStatus.OK);
    }
}
