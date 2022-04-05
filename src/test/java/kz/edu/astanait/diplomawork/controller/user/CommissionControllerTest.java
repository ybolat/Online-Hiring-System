package kz.edu.astanait.diplomawork.controller.user;

import kz.edu.astanait.diplomawork.service.serviceInterface.user.CommissionService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommissionControllerTest {

    private final CommissionService commissionService;

    CommissionControllerTest(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @Test
    void getById() {
        Long id = 1L;
        assertFalse(this.commissionService.getById(id).isEmpty());
    }

    @Test
    void getAll() {
    }
}