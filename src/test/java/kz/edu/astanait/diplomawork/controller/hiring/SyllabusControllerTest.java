package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SyllabusControllerTest {

    private final SyllabusService syllabusService;

    SyllabusControllerTest(SyllabusService syllabusService) {
        this.syllabusService = syllabusService;
    }

    @Test
    void getAllByUserProfessionalInfoId() {
        Long id = 1L;
        assertFalse(this.syllabusService.getAllByUserId(id).isEmpty());
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