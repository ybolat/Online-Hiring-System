package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.StatusDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.StatusMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/status")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<StatusDtoResponse>> getAll() {
        List<StatusDtoResponse> statusDtoResponseList = statusService.getAll()
                .stream().map(StatusMapper::statusToDto).collect(Collectors.toList());
        return new ResponseEntity<>(statusDtoResponseList, HttpStatus.OK);
    }
}
