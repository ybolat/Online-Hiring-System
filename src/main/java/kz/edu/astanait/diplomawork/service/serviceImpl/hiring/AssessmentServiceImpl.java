package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.AssessmentDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Assessment;
import kz.edu.astanait.diplomawork.repository.hiring.AssessmentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.AssessmentService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.RequestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final RequestService requestService;

    @Autowired
    public AssessmentServiceImpl(AssessmentRepository assessmentRepository, RequestService requestService) {
        this.assessmentRepository = assessmentRepository;
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

        assessment.setRequest(this.requestService.getByIdThrowException(assessmentDtoRequest.getRequestId()));
        assessment.setVote(assessmentDtoRequest.getVote());

        try{
            this.assessmentRepository.save(assessment);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "assessment"));
        }
    }

    @Override
    public void createAll(List<AssessmentDtoRequest> assessmentDtoRequestList) {
        List<Assessment> assessmentList = new ArrayList<>();

        for (AssessmentDtoRequest assessmentDtoRequest: assessmentDtoRequestList) {
            Assessment assessment = new Assessment();

            assessment.setRequest(this.requestService.getByIdThrowException(assessmentDtoRequest.getRequestId()));
            assessment.setVote(assessmentDtoRequest.getVote());

            assessmentList.add(assessment);
        }

        try {
            this.assessmentRepository.saveAll(assessmentList);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "assessment list"));
        }
    }
}
