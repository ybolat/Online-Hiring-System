package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.repository.hiring.AssessmentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;

    @Autowired
    public AssessmentServiceImpl(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }
}
