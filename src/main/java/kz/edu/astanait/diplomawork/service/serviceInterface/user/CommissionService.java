package kz.edu.astanait.diplomawork.service.serviceInterface.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.CommissionAuthorizationDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.user.CommissionDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.user.CommissionDtoResponse;
import kz.edu.astanait.diplomawork.model.user.Commission;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface CommissionService {

    List<Commission> getAll();

    Optional<Commission> getById(Long id);

    Commission getByIdThrowException(Long id);

    Optional<Commission> getByEmail(String email);

    Commission getByEmailThrowException(String email);

    void create(CommissionDtoRequest commissionDtoRequest);

    void update(Long id, CommissionDtoRequest commissionDtoRequest);

    void delete(Long id);

    ResponseEntity<CommissionDtoResponse> authorization(CommissionAuthorizationDtoRequest commissionAuthorizationDtoRequest, HttpServletRequest request);
}
