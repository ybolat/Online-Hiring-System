package kz.edu.astanait.diplomawork.controller.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserRegistrationDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.UserMapper;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

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

    @PostMapping("/activate")
    public ResponseEntity<UserDtoResponse> activate(@RequestParam(name = "email") String email,
                                                    @RequestParam(name = "pin_code") Integer pinCode) {
        User user = this.userService.activate(email, pinCode);
        UserDtoResponse userDtoResponse = UserMapper.userToDto(user);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }
}
