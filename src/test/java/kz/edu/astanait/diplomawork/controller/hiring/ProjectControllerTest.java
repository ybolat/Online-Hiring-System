package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.ProjectService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectControllerTest {

    private final ProjectService projectService;

    ProjectControllerTest(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Test
    void getAllByUserProfessionalInfoId() {
        Long id = 1L;
        assertFalse(this.projectService.getAllByUserProfessionalInfoId(id).isEmpty());
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