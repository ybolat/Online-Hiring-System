package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.AssessmentDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Assessment;
import kz.edu.astanait.diplomawork.repository.hiring.AssessmentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.AssessmentService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.RequestService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;

    private final CommissionService commissionService;
    private final RequestService requestService;

    @Autowired
    public AssessmentServiceImpl(AssessmentRepository assessmentRepository, CommissionService commissionService, RequestService requestService) {
        this.assessmentRepository = assessmentRepository;
        this.commissionService = commissionService;
        this.requestService = requestService;
    }

    @Override
    public List<Assessment> getAllByRequestId(Long id) {
        return this.assessmentRepository.findAllByRequestId(id);
    }

    @Override
    public Optional<Assessment> getById(Long id) {
        return this.assessmentRepository.findById(id);
    }

    @Override
    public Assessment getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                (String.format(ExceptionDescription.CustomNotFoundException, "Assessment", "id", id)));
    }

    @Override
    public void create(AssessmentDtoRequest assessmentDtoRequest) {
        Assessment assessment = new Assessment();

        assessment.setCommission(this.commissionService.getByIdThrowException(assessmentDtoRequest.getCommissionId()));
        assessment.setRequest(this.requestService.getByIdThrowException(assessmentDtoRequest.getRequestId()));
        assessment.setRate(assessmentDtoRequest.getRate());

        try{
            this.assessmentRepository.save(assessment);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "assessment"));
        }
    }

    @Override
    public void update(AssessmentDtoRequest assessmentDtoRequest, Long id) {
        Assessment assessment = this.getByIdThrowException(id);

        if(Objects.nonNull(assessmentDtoRequest.getRate())) assessment.setRate(assessmentDtoRequest.getRate());

        try {
            this.assessmentRepository.save(assessment);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "assessment"));
        }
    }

    @Override
    public void delete(Long id) {
        Assessment assessment = this.getByIdThrowException(id);

        try {
            this.assessmentRepository.delete(assessment);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "assessment"));
        }
    }
}
