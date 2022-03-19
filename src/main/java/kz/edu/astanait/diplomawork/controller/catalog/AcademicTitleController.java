package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicTitleDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicTitleMapper;
import kz.edu.astanait.diplomawork.model.catalog.AcademicTitle;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/academic-title")
public class AcademicTitleController {

    private final AcademicTitleService academicTitleService;

    @Autowired
    public AcademicTitleController(AcademicTitleService academicTitleService) {
        this.academicTitleService = academicTitleService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AcademicTitleDtoResponse>> getAll() {
        List<AcademicTitleDtoResponse> academicTitleDtoResponseList = this.academicTitleService.getAll().
                stream().map(AcademicTitleMapper::academicTitleToDto).collect(Collectors.toList());
        return new ResponseEntity<>(academicTitleDtoResponseList, HttpStatus.OK);
    }
}
