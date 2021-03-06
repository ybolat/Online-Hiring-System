package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.requestDto.catalog.DepartmentDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.DepartmentDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.catalog.DepartmentMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/department")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_ADMIN')")
public class DepartmentController extends ExceptionHandling {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<DepartmentDtoResponse>> getAll() {
        List<DepartmentDtoResponse> departmentDtoResponseList = this.departmentService.getAll().
                stream().map(DepartmentMapper::departmentToDto).collect(Collectors.toList());
        return new ResponseEntity<>(departmentDtoResponseList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody DepartmentDtoRequest departmentDtoRequest) {
        this.departmentService.create(departmentDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody DepartmentDtoRequest departmentDtoRequest,
                                             @PathVariable(name = "id") Long id) {
        this.departmentService.update(departmentDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
