package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.VacancyDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.VacancyDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.hiring.VacancyMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/vacancy")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
public class VacancyController extends ExceptionHandling {

    private final VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/get-all")
    public ResponseEntity<List<VacancyDtoResponse>> getAll() {
        List<VacancyDtoResponse> vacancyDtoResponseList = this.vacancyService.getAll().stream().
                map(VacancyMapper::vacancyToDto).collect(Collectors.toList());
        return new ResponseEntity<>(vacancyDtoResponseList, HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/get-all/valid")
    public ResponseEntity<List<VacancyDtoResponse>> getAllValid() {
        List<VacancyDtoResponse> vacancyDtoResponseList = this.vacancyService.getAllValid().stream().
                map(VacancyMapper::vacancyToDto).collect(Collectors.toList());
        return new ResponseEntity<>(vacancyDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<VacancyDtoResponse> getById(@PathVariable(name = "id") Long id) {
        VacancyDtoResponse vacancyDtoResponse = VacancyMapper.vacancyToDto(this.vacancyService.getByIdThrowException(id));
        return new ResponseEntity<>(vacancyDtoResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody VacancyDtoRequest vacancyDtoRequest) {
        this.vacancyService.create(vacancyDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody VacancyDtoRequest vacancyDtoRequest,
                                             @PathVariable(name = "id") Long id) {
        this.vacancyService.update(vacancyDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.vacancyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create/all")
    public ResponseEntity<HttpStatus> createAll(@RequestBody List<VacancyDtoRequest> vacancyDtoRequest) {
        this.vacancyService.createAll(vacancyDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
