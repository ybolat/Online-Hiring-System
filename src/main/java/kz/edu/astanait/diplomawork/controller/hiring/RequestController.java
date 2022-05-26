package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.RequestDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.RequestDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.hiring.RequestMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/request")
@PreAuthorize("hasAnyRole('ROLE_CHALLENGER', 'ROLE_COMMISSION')")
public class RequestController extends ExceptionHandling {

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

    @GetMapping("/get/user/id/{id}")
    public ResponseEntity<RequestDtoResponse> getByUserId(@PathVariable(name = "id") Long id) {
        RequestDtoResponse requestDtoResponse = RequestMapper.requestToDto(this.requestService.getByUserIdThrowException(id));
        return new ResponseEntity<>(requestDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/get/percentage-of-accepted")
    public ResponseEntity<Double> getPercentageOfAcceptedRequestSince(@RequestParam(name = "date") LocalDateTime startedDate,
                                                                      @RequestParam(name = "statusID") Long id) {
        Double percentage = this.requestService.getPercentageOfAcceptedRequestSince(startedDate, id);
        return new ResponseEntity<>(percentage, HttpStatus.OK);
    }

    @GetMapping("/get/page/{num}")
    public ResponseEntity<List<RequestDtoResponse>> getLimited(@PathVariable(name = "num") int num) {
        List<RequestDtoResponse> requestDtoResponseList = this.requestService.getAll()
                .stream().map(RequestMapper::requestToDto).collect(Collectors.toList());
        List<RequestDtoResponse> result = new ArrayList<>();

        for (int i = (num - 1) * 2; i < num * 3; i++) {
            result.add(requestDtoResponseList.get(i));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
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

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody RequestDtoRequest requestDtoRequest, Principal principal) {
        this.requestService.create(requestDtoRequest, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/create/all")
    public ResponseEntity<HttpStatus> createAll(@RequestBody List<RequestDtoRequest> requestDtoRequestList,
                                                Principal principal) {
        this.requestService.createAll(requestDtoRequestList, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
