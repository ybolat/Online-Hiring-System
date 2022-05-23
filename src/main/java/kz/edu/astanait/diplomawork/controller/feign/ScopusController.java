package kz.edu.astanait.diplomawork.controller.feign;

import kz.edu.astanait.diplomawork.service.serviceInterface.feign.ScopusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/v1/scopus")
public class ScopusController {

    private final ScopusService scopusService;

    @Autowired
    public ScopusController(ScopusService scopusService) {
        this.scopusService = scopusService;
    }

    @GetMapping ("/get/information")
    public ResponseEntity<HttpStatus> getScopusInformation(Principal principal) {
        Boolean result = this.scopusService.getScopusInformation(principal);
        if (result) return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/get/author/information")
    public ResponseEntity<HttpStatus> getAuthorInformation(Principal principal) {
        AtomicReference<Boolean> result = this.scopusService.getAuthorInformation(principal);
        if (result.get()) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
