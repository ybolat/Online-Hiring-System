package kz.edu.astanait.diplomawork.controller.security;

import kz.edu.astanait.diplomawork.service.serviceInterface.security.RegistrationPinCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration-pin-code")
public class RegistrationPinCodeController {

    private final RegistrationPinCodeService registrationPinCodeService;

    @Autowired
    public RegistrationPinCodeController(RegistrationPinCodeService registrationPinCodeService) {
        this.registrationPinCodeService = registrationPinCodeService;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestParam(name = "email") String email) {
        this.registrationPinCodeService.create(email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestParam(name = "email") String email) {
        this.registrationPinCodeService.update(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
