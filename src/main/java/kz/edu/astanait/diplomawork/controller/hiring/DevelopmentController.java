package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.DevelopmentDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.DevelopmentMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/development")
public class DevelopmentController {

    private final DevelopmentService developmentService;

    @Autowired
    public DevelopmentController(DevelopmentService developmentService) {
        this.developmentService = developmentService;
    }

    @GetMapping("/get/user/id/{id}")
    public ResponseEntity<List<DevelopmentDtoResponse>> getAllByUserId(@PathVariable(name = "id") Long id) {
        List<DevelopmentDtoResponse> developmentDtoResponseList =
                developmentService.getAllByUserId(id).stream().map(DevelopmentMapper::developmentToDto).collect(Collectors.toList());
        return new ResponseEntity<>(developmentDtoResponseList, HttpStatus.OK);
    }
}
