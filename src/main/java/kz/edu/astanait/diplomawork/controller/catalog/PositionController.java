package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.PositionDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.PositionMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/position")
public class PositionController {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<PositionDtoResponse>> getAll() {
        List<PositionDtoResponse> positionDtoResponseList = this.positionService.getAll().
                stream().map(PositionMapper::positionToDto).collect(Collectors.toList());
        return new ResponseEntity<>(positionDtoResponseList, HttpStatus.OK);
    }
}
