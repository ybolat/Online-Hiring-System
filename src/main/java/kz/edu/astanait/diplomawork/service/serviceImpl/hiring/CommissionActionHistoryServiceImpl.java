package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.CommissionActionHistoryDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.CommissionActionHistory;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CommissionActionHistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CommissionActionHistoryServiceImpl implements CommissionActionHistoryService {
    @Override
    public List<CommissionActionHistory> getAllByUserProfessionalInfoId(Long id) {
        return null;
    }

    @Override
    public Optional<CommissionActionHistory> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public CommissionActionHistory getByIdThrowException(Long id) {
        return null;
    }

    @Override
    public void create(CommissionActionHistoryDtoRequest commissionActionHistoryDtoRequest) {

    }
}
