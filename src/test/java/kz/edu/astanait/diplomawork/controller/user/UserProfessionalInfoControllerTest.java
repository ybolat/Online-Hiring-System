package kz.edu.astanait.diplomawork.controller.user;

import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserProfessionalInfoControllerTest {

    private final UserProfessionalInfoService userProfessionalInfoService;

    UserProfessionalInfoControllerTest(UserProfessionalInfoService userProfessionalInfoService) {
        this.userProfessionalInfoService = userProfessionalInfoService;
    }

    @Test
    void getByUserId() {
        Long id = 1L;
        assertFalse(this.userProfessionalInfoService.getByUserId(id).isEmpty());
    }

    @Test
    void createProfile() {
    }

    @Test
    void updateProfile() {
    }
}