package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicDegreeDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicDegreeMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/academic-degree")
public class AcademicDegreeController {

    private final AcademicDegreeService academicDegreeService;

    @Autowired
    public AcademicDegreeController(AcademicDegreeService academicDegreeService) {
        this.academicDegreeService = academicDegreeService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AcademicDegreeDtoResponse>> getAll() {
        List<AcademicDegreeDtoResponse> academicDegreeDtoResponseList = this.academicDegreeService.getAll()
                .stream().map(AcademicDegreeMapper::academicDegreeToDto).collect(Collectors.toList());
        return new ResponseEntity<>(academicDegreeDtoResponseList, HttpStatus.OK);
    }
}
