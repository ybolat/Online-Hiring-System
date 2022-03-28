package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.CommissionActionHistoryDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.CommissionActionHistory;
import kz.edu.astanait.diplomawork.repository.hiring.CommissionActionHistoryRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CommissionActionHistoryService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.RequestService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.CommissionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CommissionActionHistoryServiceImpl implements CommissionActionHistoryService {

    private final CommissionActionHistoryRepository commissionActionHistoryRepository;

    private final CommissionService commissionService;
    private final RequestService requestService;

    public CommissionActionHistoryServiceImpl(CommissionActionHistoryRepository commissionActionHistoryRepository, CommissionService commissionService, RequestService requestService) {
        this.commissionActionHistoryRepository = commissionActionHistoryRepository;
        this.commissionService = commissionService;
        this.requestService = requestService;
    }

    @Override
    public Optional<CommissionActionHistory> getById(Long id) {
        return this.commissionActionHistoryRepository.findById(id);
    }

    @Override
    public CommissionActionHistory getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException(String.format(ExceptionDescription.CustomNotFoundException, "Commission action history", "id", id)));
    }

    @Override
    public List<CommissionActionHistory> getByRequestId(Long id) {
        return this.commissionActionHistoryRepository.findByRequestId(id);
    }

    @Override
    public void create(CommissionActionHistoryDtoRequest commissionActionHistoryDtoRequest) {
        CommissionActionHistory commissionActionHistory = new CommissionActionHistory();

        commissionActionHistory.setCommission(this.commissionService.getByIdThrowException(commissionActionHistoryDtoRequest.getCommissionId()));
        commissionActionHistory.setRequest(this.requestService.getByIdThrowException(commissionActionHistoryDtoRequest.getRequestId()));
        commissionActionHistory.setIsVote(commissionActionHistoryDtoRequest.getIsVote());

        try{
            this.commissionActionHistoryRepository.save(commissionActionHistory);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "commission action history"));
        }
    }
}
