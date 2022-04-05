package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.IntelligenceLegalDocumentService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntelligenceLegalDocumentControllerTest {

    private final IntelligenceLegalDocumentService intelligenceLegalDocumentService;

    IntelligenceLegalDocumentControllerTest(IntelligenceLegalDocumentService intelligenceLegalDocumentService) {
        this.intelligenceLegalDocumentService = intelligenceLegalDocumentService;
    }

    @Test
    void getAllByUserProfessionalInfoId() {
        Long id = 1L;
        assertFalse(this.intelligenceLegalDocumentService.getAllByUserProfessionalInfoId(id).isEmpty());
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