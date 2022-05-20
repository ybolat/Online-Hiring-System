package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.CommissionActionHistoryDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.CommissionActionHistoryDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.hiring.CommissionActionHistoryMapper;
import kz.edu.astanait.diplomawork.model.hiring.CommissionActionHistory;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CommissionActionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/commission-action-history")
@PreAuthorize("hasRole('ROLE_COMMISSION')")
public class CommissionActionHistoryController extends ExceptionHandling {

    private final CommissionActionHistoryService commissionActionHistoryService;

    @Autowired
    public CommissionActionHistoryController(CommissionActionHistoryService commissionActionHistoryService) {
        this.commissionActionHistoryService = commissionActionHistoryService;
    }

    @GetMapping("/get/request-id/{id}/")
    public ResponseEntity<List<CommissionActionHistoryDtoResponse>> getByCommissionIdAndRequestId(@PathVariable(name = "id") Long id) {
        List<CommissionActionHistoryDtoResponse> commissionActionHistoryDtoResponseList =
                this.commissionActionHistoryService.getByRequestId(id).stream().
                        map(CommissionActionHistoryMapper::commissionActionHistoryToDto).collect(Collectors.toList());
        return new ResponseEntity<>(commissionActionHistoryDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("/get/commission-id/{c-id}/request-id/{r-id}")
    public ResponseEntity<CommissionActionHistoryDtoResponse> getByCommissionIdAndRequestIdThrowException(
            @PathVariable(name = "c-id") Long cId, @PathVariable(name = "r-id") Long rId) {
        CommissionActionHistoryDtoResponse commissionActionHistoryDtoResponse = CommissionActionHistoryMapper.
                commissionActionHistoryToDto(this.commissionActionHistoryService.getByCommissionIdAndRequestIdThrowException
                        (cId, rId));
        return new ResponseEntity<>(commissionActionHistoryDtoResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody CommissionActionHistoryDtoRequest commissionActionHistoryDtoRequest){
        this.commissionActionHistoryService.create(commissionActionHistoryDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    public ResponseEntity<HttpStatus> createAll(@Valid @RequestBody List<CommissionActionHistoryDtoRequest> commissionActionHistoryDtoRequestList){
//        this.commissionActionHistoryService.createAll();
//    }

    @PostMapping("/create/all")
    public ResponseEntity<HttpStatus> createAll(@Valid @RequestBody List<CommissionActionHistoryDtoRequest> commissionActionHistoryDtoRequestList){
        this.commissionActionHistoryService.createAll(commissionActionHistoryDtoRequestList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}


