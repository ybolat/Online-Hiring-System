package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.CommissionActionHistoryDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CommissionActionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hiring/commission-action-history")
@PreAuthorize("hasRole('ROLE_COMMISSION')")
public class CommissionActionHistoryController extends ExceptionHandling {

    private final CommissionActionHistoryService commissionActionHistoryService;

    @Autowired
    public CommissionActionHistoryController(CommissionActionHistoryService commissionActionHistoryService) {
        this.commissionActionHistoryService = commissionActionHistoryService;
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


