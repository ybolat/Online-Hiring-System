package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusByWeekDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.SyllabusDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.hiring.SyllabusMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/syllabus")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
public class SyllabusController extends ExceptionHandling {

    private final SyllabusService syllabusService;

    @Autowired
    public SyllabusController(SyllabusService syllabusService) {
        this.syllabusService = syllabusService;
    }

    @GetMapping("/get/user-professional-info/id/{id}")
    public ResponseEntity<List<SyllabusDtoResponse>> getAllByUserProfessionalInfoId(@PathVariable(name = "id") Long id) {
        List<SyllabusDtoResponse> syllabusDtoResponseList =
                this.syllabusService.getAllByUserId(id).stream().map(SyllabusMapper::syllabusToDto).collect(Collectors.toList());
        return new ResponseEntity<>(syllabusDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody SyllabusDtoRequest syllabusDtoRequest, Principal principal) {
        this.syllabusService.create(syllabusDtoRequest, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody SyllabusDtoRequest syllabusDtoRequest,
                                             @PathVariable(name = "id") Long id,
                                             @RequestParam(name = "SyllabusByWeekList") HashMap<Long, SyllabusByWeekDtoRequest> syllabusByWeekDtoRequestHashMap) {
        this.syllabusService.update(syllabusDtoRequest, id, syllabusByWeekDtoRequestHashMap);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.syllabusService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create/all")
    public ResponseEntity<HttpStatus> createAll(@Valid @RequestBody List<SyllabusDtoRequest> syllabusDtoRequest, Principal principal) {
        this.syllabusService.createAll(syllabusDtoRequest, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
