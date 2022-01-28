package kz.edu.astanait.diplomawork.controller.user;

import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/user-professional-info")
public class UserProfessionalInfoController {

    private final UserProfessionalInfoService userProfessionalInfoService;

    @Autowired
    public UserProfessionalInfoController(UserProfessionalInfoService userProfessionalInfoService) {
        this.userProfessionalInfoService = userProfessionalInfoService;
    }
}
