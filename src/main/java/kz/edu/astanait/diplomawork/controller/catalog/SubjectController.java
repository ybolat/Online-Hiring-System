package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.SubjectDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.SubjectMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<SubjectDtoResponse>> getAllSubject() {
        List<SubjectDtoResponse> subjectDtoResponseList = this.subjectService.getAll()
                .stream().map(SubjectMapper::subjectToDto).collect(Collectors.toList());
        return new ResponseEntity<>(subjectDtoResponseList, HttpStatus.OK);
    }
}
