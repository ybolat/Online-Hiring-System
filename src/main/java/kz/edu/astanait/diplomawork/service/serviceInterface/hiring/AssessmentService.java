package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Assessment;

import java.util.List;

public interface AssessmentService {

    List<Assessment> getAllByRequestId(Long id);
}
