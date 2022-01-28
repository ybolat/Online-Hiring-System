package kz.edu.astanait.diplomawork.controller.user;

import kz.edu.astanait.diplomawork.dto.responseDto.user.CommissionDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.CommissionMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/commission")
public class CommissionController {

    private final CommissionService commissionService;

    @Autowired
    public CommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<CommissionDtoResponse> getById(@PathVariable (name = "id") Long id) {
        CommissionDtoResponse commissionDtoResponse = CommissionMapper.commissionToDto(commissionService.getByIdThrowException(id));
        return new ResponseEntity<>(commissionDtoResponse, HttpStatus.OK);
    }
}
