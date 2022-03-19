package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.AssessmentDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.hiring.CommissionActionHistoryDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.CommissionActionHistory;
import java.util.List;
import java.util.Optional;

public interface CommissionActionHistoryService {

    List<CommissionActionHistory> getAllByUserProfessionalInfoId(Long id);

    Optional<CommissionActionHistory> getById(Long id);

    CommissionActionHistory getByIdThrowException(Long id);

    void create(CommissionActionHistoryDtoRequest commissionActionHistoryDtoRequest);

//    void update(CommissionActionHistoryDtoRequest commissionActionHistoryDtoRequest, Long id);
//
//    void delete(Long id);
}