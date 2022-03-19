package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.CommissionActionHistoryDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.CertificateMapper;
import kz.edu.astanait.diplomawork.mapper.hiring.CommissionActionHistoryMapper;
import kz.edu.astanait.diplomawork.mapper.user.CommissionMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CertificateService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CommissionActionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hiring/commission-action-history")
public class CommissionActionHistoryController {

    private final CommissionActionHistoryService commissionActionHistoryService;

    @Autowired
    public CommissionActionHistoryController(CommissionActionHistoryService commissionActionHistoryService) {
        this.commissionActionHistoryService = commissionActionHistoryService;
    }

    public ResponseEntity<List<CommissionActionHistoryDtoResponse>> getAllByUserProfessionalInfoId(@PathVariable(name = "id") Long id){
        List<CommissionActionHistoryDtoResponse> commissionActionHistoryDtoResponseList =
                this.commissionActionHistoryService.getAllByUserProfessionalInfoId(id).stream().map(CommissionActionHistoryMapper::commissionActionHistoryToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(commissionActionHistoryDtoResponseList, HttpStatus.OK);
    }
}
