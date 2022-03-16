package kz.edu.astanait.diplomawork.controller.user;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserProfessionalInfoDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.UserProfessionalInfoMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/get/user/id/{id}")
    public ResponseEntity<UserProfessionalInfoDtoResponse> getByUserId(@PathVariable(name = "id") Long id) {
        UserProfessionalInfoDtoResponse userProfessionalInfoDtoResponse = UserProfessionalInfoMapper.
                userProfessionalInfoToDto(this.userProfessionalInfoService.getByUserIdThrowException(id));
        return new ResponseEntity<>(userProfessionalInfoDtoResponse, HttpStatus.OK);
    }
}
