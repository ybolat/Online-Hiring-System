package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.VacancyDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.VacancyDtoResponse;
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
public class VacancyController {

    private final VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<VacancyDtoResponse>> getAll() {
        List<VacancyDtoResponse> vacancyDtoResponseList = this.vacancyService.getAll().stream().
                map(VacancyMapper::vacancyToDto).collect(Collectors.toList());
        return new ResponseEntity<>(vacancyDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody VacancyDtoRequest vacancyDtoRequest) {
        this.vacancyService.create(vacancyDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody VacancyDtoRequest vacancyDtoRequest,
                                             @PathVariable(name = "id") Long id) {
        this.vacancyService.update(vacancyDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.vacancyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
