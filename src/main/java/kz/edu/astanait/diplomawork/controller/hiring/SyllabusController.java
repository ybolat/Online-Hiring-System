package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.SyllabusDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.SyllabusMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusService;
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
@RequestMapping("/api/v1/hiring/syllabus")
public class SyllabusController {

    private final SyllabusService syllabusService;

    @Autowired
    public SyllabusController(SyllabusService syllabusService) {
        this.syllabusService = syllabusService;
    }

    @GetMapping("/get/user/id/{id}")
    public ResponseEntity<List<SyllabusDtoResponse>> getAllByUserId(@PathVariable(name = "id") Long id) {
        List<SyllabusDtoResponse> syllabusDtoResponseList =
                this.syllabusService.getAllByUserId(id).stream().map(SyllabusMapper::syllabusToDto).collect(Collectors.toList());
        return new ResponseEntity<>(syllabusDtoResponseList, HttpStatus.OK);
    }
}
