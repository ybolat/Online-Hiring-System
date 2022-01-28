package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.SyllabusByWeekDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.SyllabusDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.SyllabusByWeekMapper;
import kz.edu.astanait.diplomawork.repository.hiring.SyllabusByWeekRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusByWeekService;
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
                = syllabusByWeekService.getAllBySyllabusId(id).stream().map(SyllabusByWeekMapper::syllabusByWeekToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(syllabusByWeekDtoResponseList, HttpStatus.OK);
    }
}
