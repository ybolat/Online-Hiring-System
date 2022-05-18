package kz.edu.astanait.diplomawork.controller.share;

import kz.edu.astanait.diplomawork.dto.responseDto.share.UserDataDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.service.serviceInterface.share.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-data")
public class UserDataController extends ExceptionHandling {

    private final UserDataService userDataService;

    @Autowired
    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("/get")
    public ResponseEntity<UserDataDtoResponse> getData(String secretKey, Long id){
        UserDataDtoResponse userDataDtoResponse = this.userDataService.getData(secretKey, id);
        return new ResponseEntity<>(userDataDtoResponse, HttpStatus.OK);
    }
}
