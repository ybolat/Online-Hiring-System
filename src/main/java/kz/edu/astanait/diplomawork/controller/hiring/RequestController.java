package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.RequestDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.RequestDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.RequestMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/request")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<RequestDtoResponse>> getAll() {
        List<RequestDtoResponse> requestDtoResponseList = this.requestService.getAll()
                .stream().map(RequestMapper::requestToDto).collect(Collectors.toList());
        return new ResponseEntity<>(requestDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody RequestDtoRequest requestDtoRequest) {
        this.requestService.create(requestDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody RequestDtoRequest requestDtoRequest,
                                             @PathVariable(name = "id") Long id) {
        this.requestService.update(requestDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.requestService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-all/status/id/{id}")
    public ResponseEntity<List<RequestDtoResponse>> getAllByStatus(@PathVariable(name = "id") Long id) {
        List<RequestDtoResponse> requestDtoResponseList = this.requestService.getAllByStatus(id).stream().
                map(RequestMapper::requestToDto).collect(Collectors.toList());
        return new ResponseEntity<>(requestDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get-all/created-date")
    public ResponseEntity<List<RequestDtoResponse>> getAllOrderByCreatedDate(LocalDateTime createdDate) {
        List<RequestDtoResponse> requestDtoResponseList = this.requestService.getAllOrderByCreatedDate(createdDate).stream().
                map(RequestMapper::requestToDto).collect(Collectors.toList());
        return new ResponseEntity<>(requestDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get-all/created-date/desc")
    public ResponseEntity<List<RequestDtoResponse>> getAllOrderByCreatedDateDesc(LocalDateTime createdDate) {
        List<RequestDtoResponse> requestDtoResponseList = this.requestService.getAllOrderByCreatedDateDesc(createdDate).stream().
                map(RequestMapper::requestToDto).collect(Collectors.toList());
        return new ResponseEntity<>(requestDtoResponseList, HttpStatus.OK);
    }
}
