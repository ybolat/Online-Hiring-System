package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusByWeekDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.SyllabusByWeekDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.SyllabusByWeekMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusByWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/syllabus-by-week")
public class SyllabusByWeekController {

    private final SyllabusByWeekService syllabusByWeekService;

    @Autowired
    public SyllabusByWeekController(SyllabusByWeekService syllabusByWeekService) {
        this.syllabusByWeekService = syllabusByWeekService;
    }

    @GetMapping("/get/syllabus/id/{id}")
    public ResponseEntity<List<SyllabusByWeekDtoResponse>> getAllBySyllabusId(@PathVariable(name = "id") Long id) {
        List<SyllabusByWeekDtoResponse> syllabusByWeekDtoResponseList
                = this.syllabusByWeekService.getAllBySyllabusId(id).stream().map(SyllabusByWeekMapper::syllabusByWeekToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(syllabusByWeekDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody SyllabusByWeekDtoRequest syllabusByWeekDtoRequest) {
        this.syllabusByWeekService.create(syllabusByWeekDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody SyllabusByWeekDtoRequest syllabusByWeekDtoRequest,
                                             @PathVariable(name = "id") Long id) {
        this.syllabusByWeekService.update(syllabusByWeekDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.syllabusByWeekService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
