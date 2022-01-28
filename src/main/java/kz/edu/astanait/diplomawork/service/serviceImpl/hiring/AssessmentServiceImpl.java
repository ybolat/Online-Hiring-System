package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Assessment;
import kz.edu.astanait.diplomawork.repository.hiring.AssessmentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;

    @Autowired
    public AssessmentServiceImpl(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public List<Assessment> getAllByRequestId(Long id) {
        return assessmentRepository.findAllByRequestId(id);
    }
}
