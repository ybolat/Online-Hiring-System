package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusByWeekService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SyllabusByWeekControllerTest {

    private final SyllabusByWeekService syllabusByWeekService;

    SyllabusByWeekControllerTest(SyllabusByWeekService syllabusByWeekService) {
        this.syllabusByWeekService = syllabusByWeekService;
    }

    @Test
    void getAllBySyllabusId() {
        Long id = 1L;
        assertFalse(this.syllabusByWeekService.getAllBySyllabusId(id).isEmpty());
    }

    @Test
    void create() {
    }
}