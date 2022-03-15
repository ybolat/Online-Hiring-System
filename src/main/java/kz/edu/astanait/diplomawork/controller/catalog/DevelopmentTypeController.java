package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.DevelopmentTypeDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.DevelopmentTypeMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.DevelopmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/development-type")
public class DevelopmentTypeController {

    private final DevelopmentTypeService developmentTypeService;

    @Autowired
    public DevelopmentTypeController(DevelopmentTypeService developmentTypeService) {
        this.developmentTypeService = developmentTypeService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<DevelopmentTypeDtoResponse>> getAll() {
        List<DevelopmentTypeDtoResponse> developmentTypeDtoResponseList = this.developmentTypeService.getAll().
                stream().map(DevelopmentTypeMapper::developmentTypeToDto).collect(Collectors.toList());
        return new ResponseEntity<>(developmentTypeDtoResponseList, HttpStatus.OK);
    }
}
