package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicDegreeDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicDegreeMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicDegreeService;
import org.springdoc.api.annotations.ParameterObject;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/academic-degree")
@PreAuthorize("hasRole('ROLE_CHALLENGER')")
public class AcademicDegreeController extends ExceptionHandling {

    private final AcademicDegreeService academicDegreeService;

    @Autowired
    public AcademicDegreeController(AcademicDegreeService academicDegreeService) {
        this.academicDegreeService = academicDegreeService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AcademicDegreeDtoResponse>> getAll(@ParameterObject Pageable pageable) {
        List<AcademicDegreeDtoResponse> academicDegreeDtoResponseList = this.academicDegreeService.getAll()
                .stream().map(AcademicDegreeMapper::academicDegreeToDto).collect(Collectors.toList());
        return new ResponseEntity<>(academicDegreeDtoResponseList, HttpStatus.OK);
    }
}
