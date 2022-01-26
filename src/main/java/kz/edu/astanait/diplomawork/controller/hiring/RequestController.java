package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.RequestDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.RequestMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/get-all-request")
    public ResponseEntity<List<RequestDtoResponse>> getAllRequest() {
        List<RequestDtoResponse> requestDtoResponseList = requestService.getAllRequest()
                .stream().map(RequestMapper::requestToDto).collect(Collectors.toList());
        return new ResponseEntity<>(requestDtoResponseList, HttpStatus.OK);
    }
}
