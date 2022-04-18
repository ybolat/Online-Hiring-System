package kz.edu.astanait.diplomawork.controller.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserProfessionalInfoDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.user.UserProfessionalInfoDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.user.UserProfessionalInfoMapper;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/user-professional-info")
public class UserProfessionalInfoController extends ExceptionHandling {

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

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createProfile(@Valid @RequestBody UserProfessionalInfoDtoRequest userProfessionalInfoDtoRequest){
        this.userProfessionalInfoService.createProfile(userProfessionalInfoDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<HttpStatus> updateProfile(@RequestBody UserProfessionalInfoDtoRequest userProfessionalInfoDtoRequest,
                                                    @PathVariable(name = "id") Long id){
        this.userProfessionalInfoService.updateProfile(userProfessionalInfoDtoRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
