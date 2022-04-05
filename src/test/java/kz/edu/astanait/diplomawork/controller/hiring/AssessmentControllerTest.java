package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.AssessmentService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AssessmentControllerTest {

    private final AssessmentService assessmentService;

    AssessmentControllerTest(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @Test
    void getAllByRequestId() {
        Long id = 1L;
        assertFalse(this.assessmentService.getAllByRequestId(id).isEmpty());
    }

    @Test
    void create() {
    }
}