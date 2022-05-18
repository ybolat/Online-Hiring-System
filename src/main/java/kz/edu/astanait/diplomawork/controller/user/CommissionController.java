package kz.edu.astanait.diplomawork.controller.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.CommissionDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.user.CommissionDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.user.CommissionMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user/commission")
public class CommissionController extends ExceptionHandling {

    private final CommissionService commissionService;

    @Autowired
    public CommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<CommissionDtoResponse> getById(@PathVariable (name = "id") Long id) {
        CommissionDtoResponse commissionDtoResponse = CommissionMapper.commissionToDto(this.commissionService.getByIdThrowException(id));
        return new ResponseEntity<>(commissionDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CommissionDtoResponse>> getAll() {
        List<CommissionDtoResponse> commissionDtoResponseList = this.commissionService.getAll().stream().
                map(CommissionMapper::commissionToDto).collect(Collectors.toList());
        return new ResponseEntity<>(commissionDtoResponseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody CommissionDtoRequest commissionDtoRequest) {
        this.commissionService.create(commissionDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable(name = "id") Long id,
                                             @RequestBody CommissionDtoRequest commissionDtoRequest) {
        this.commissionService.update(id, commissionDtoRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        this.commissionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
