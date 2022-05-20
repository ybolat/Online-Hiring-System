package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;
import kz.edu.astanait.diplomawork.dto.requestDto.hiring.CommissionActionHistoryDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.CommissionActionHistory;
import java.util.List;
import java.util.Optional;

public interface CommissionActionHistoryService {

    Optional<CommissionActionHistory> getById(Long id);

    CommissionActionHistory getByIdThrowException(Long id);

    List<CommissionActionHistory> getByRequestId(Long id);

    Optional<CommissionActionHistory> getByCommissionIdAndRequestId(Long cId, Long rId);

    CommissionActionHistory getByCommissionIdAndRequestIdThrowException(Long cId, Long rId);

    void create(CommissionActionHistoryDtoRequest commissionActionHistoryDtoRequest);

    void createAll(List<CommissionActionHistoryDtoRequest> commissionActionHistoryDtoRequestList);
}
