package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.DepartmentDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.DepartmentMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("get-all")
    public ResponseEntity<List<DepartmentDtoResponse>> getAll() {
        List<DepartmentDtoResponse> departmentDtoResponseList = this.departmentService.getAll().
                stream().map(DepartmentMapper::departmentToDto).collect(Collectors.toList());
        return new ResponseEntity<>(departmentDtoResponseList, HttpStatus.OK);
    }
}
