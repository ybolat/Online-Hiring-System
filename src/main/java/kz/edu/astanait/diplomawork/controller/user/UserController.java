package kz.edu.astanait.diplomawork.controller.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserAuthorizationDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.user.UserChangePasswordDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.user.UserRegistrationDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.mapper.user.UserMapper;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends ExceptionHandling {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDtoResponse> registration(@Valid @RequestBody UserRegistrationDtoRequest userDto) {
        User user = this.userService.registration(userDto);
        UserDtoResponse userDtoResponse = UserMapper.userToDto(user);

        return new ResponseEntity<>(userDtoResponse, HttpStatus.CREATED);
    }

    @PostMapping("/authorization")
    public ResponseEntity<UserDtoResponse> authorization(@Valid @RequestBody UserAuthorizationDtoRequest userDto, HttpServletRequest request) {
     return this.userService.authorization(userDto, request);
    }

    @PostMapping("/activate")
    public ResponseEntity<UserDtoResponse> activate(@RequestParam(name = "email") String email,
                                                    @RequestParam(name = "pin_code") Integer pinCode) {
        User user = this.userService.activate(email, pinCode);
        UserDtoResponse userDtoResponse = UserMapper.userToDto(user);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CHALLENGER')")
    @PutMapping("/change-password")
    public ResponseEntity<HttpStatus> changePassword(@Valid @RequestBody UserChangePasswordDtoRequest userDto,
                                                     Principal principal) {
        this.userService.changePassword(principal, userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<HttpStatus> forgotPassword(@RequestParam(name = "email") String email,
                                                     @RequestParam(name = "pin_code") Integer pinCode) throws MessagingException {
        this.userService.forgotPassword(email, pinCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
