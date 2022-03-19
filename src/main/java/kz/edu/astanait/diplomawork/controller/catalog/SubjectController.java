package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.requestDto.catalog.SubjectDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.SubjectDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.SubjectMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/subject")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_ADMIN')")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<SubjectDtoResponse>> getAllSubject() {
        List<SubjectDtoResponse> subjectDtoResponseList = this.subjectService.getAll()
                .stream().map(SubjectMapper::subjectToDto).collect(Collectors.toList());
        return new ResponseEntity<>(subjectDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get-all/academic-degree/id/{id}")
    public ResponseEntity<List<SubjectDtoResponse>> getAllByAcademicDegreeId(@PathVariable(name = "id") Long id) {
        List<SubjectDtoResponse> subjectDtoResponseList = this.subjectService.getAllByAcademicDegreeId(id).
                stream().map(SubjectMapper::subjectToDto).collect(Collectors.toList());
        return new ResponseEntity<>(subjectDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody SubjectDtoRequest subjectDtoRequest) {
        this.subjectService.create(subjectDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody SubjectDtoRequest subjectDtoRequest,
                                             @PathVariable(name = "id") Long id) {
        this.subjectService.update(subjectDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.subjectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-all/academic-degree/id/{id}/order-by/title-en")
    public ResponseEntity<List<SubjectDtoResponse>> getAllByAcademicDegreeIdOrderByTitleEn(@PathVariable(name = "id") Long id) {
        List<SubjectDtoResponse> subjectDtoResponseList = this.subjectService.getAllByAcademicDegreeIdOrderByTitleEn(id).
                stream().map(SubjectMapper::subjectToDto).collect(Collectors.toList());
        return new ResponseEntity<>(subjectDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get-all/academic-degree/id/{id}/order-by/title-ru")
    public ResponseEntity<List<SubjectDtoResponse>> getAllByAcademicDegreeIdOrderByTitleRu(@PathVariable(name = "id") Long id) {
        List<SubjectDtoResponse> subjectDtoResponseList = this.subjectService.getAllByAcademicDegreeIdOrderByTitleRu(id).
                stream().map(SubjectMapper::subjectToDto).collect(Collectors.toList());
        return new ResponseEntity<>(subjectDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get-all/academic-degree/id/{id}/order-by/title-kz")
    public ResponseEntity<List<SubjectDtoResponse>> getAllByAcademicDegreeIdOrderByTitleKz(@PathVariable(name = "id") Long id) {
        List<SubjectDtoResponse> subjectDtoResponseList = this.subjectService.getAllByAcademicDegreeIdOrderByTitleKz(id).
                stream().map(SubjectMapper::subjectToDto).collect(Collectors.toList());
        return new ResponseEntity<>(subjectDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get-all/academic-degree/id/{id}/order-by/title-en/desc")
    public ResponseEntity<List<SubjectDtoResponse>> getAllByAcademicDegreeIdOrderByTitleEnDesc(@PathVariable(name = "id") Long id) {
        List<SubjectDtoResponse> subjectDtoResponseList = this.subjectService.getAllByAcademicDegreeIdOrderByTitleEnDesc(id).
                stream().map(SubjectMapper::subjectToDto).collect(Collectors.toList());
        return new ResponseEntity<>(subjectDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get-all/academic-degree/id/{id}/order-by/title-ru/desc")
    public ResponseEntity<List<SubjectDtoResponse>> getAllByAcademicDegreeIdOrderByTitleRuDesc(@PathVariable(name = "id") Long id) {
        List<SubjectDtoResponse> subjectDtoResponseList = this.subjectService.getAllByAcademicDegreeIdOrderByTitleRuDesc(id).
                stream().map(SubjectMapper::subjectToDto).collect(Collectors.toList());
        return new ResponseEntity<>(subjectDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get-all/academic-degree/id/{id}/order-by/title-kz/desc")
    public ResponseEntity<List<SubjectDtoResponse>> getAllByAcademicDegreeIdOrderByTitleKzDesc(@PathVariable(name = "id") Long id) {
        List<SubjectDtoResponse> subjectDtoResponseList = this.subjectService.getAllByAcademicDegreeIdOrderByTitleKzDesc(id).
                stream().map(SubjectMapper::subjectToDto).collect(Collectors.toList());
        return new ResponseEntity<>(subjectDtoResponseList, HttpStatus.OK);
    }
}
