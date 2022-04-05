package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DevelopmentService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DevelopmentControllerTest {

    private final DevelopmentService developmentService;

    DevelopmentControllerTest(DevelopmentService developmentService) {
        this.developmentService = developmentService;
    }

    @Test
    void getAllByUserProfessionalInfoId() {
        Long id = 1L;
        assertFalse(this.developmentService.getAllByUserProfessionalInfoId(id).isEmpty());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}