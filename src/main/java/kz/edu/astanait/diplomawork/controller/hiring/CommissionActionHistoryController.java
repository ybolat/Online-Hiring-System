package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.CommissionActionHistoryDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.CommissionActionHistoryDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.CertificateMapper;
import kz.edu.astanait.diplomawork.mapper.hiring.CommissionActionHistoryMapper;
import kz.edu.astanait.diplomawork.mapper.user.CommissionMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CertificateService;
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
public class CommissionActionHistoryController {

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
}
