package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CertificateService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CertificateControllerTest {

    private final CertificateService certificateService;

    CertificateControllerTest(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @Test
    void getAllByUserProfessionalInfoId() {
        Long id = 1L;
        assertFalse(this.certificateService.getAllByUserProfessionalInfoId(id).isEmpty());
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