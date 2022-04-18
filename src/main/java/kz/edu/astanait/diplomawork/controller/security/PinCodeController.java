package kz.edu.astanait.diplomawork.controller.security;

import kz.edu.astanait.diplomawork.exception.ExceptionHandling;
import kz.edu.astanait.diplomawork.service.serviceInterface.security.PinCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration-pin-code")
public class PinCodeController extends ExceptionHandling {

    private final PinCodeService pinCodeService;

    @Autowired
    public PinCodeController(PinCodeService pinCodeService) {
        this.pinCodeService = pinCodeService;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestParam(name = "email") String email) {
        this.pinCodeService.create(email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
