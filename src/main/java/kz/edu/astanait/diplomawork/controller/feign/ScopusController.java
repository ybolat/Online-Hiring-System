package kz.edu.astanait.diplomawork.controller.feign;

import kz.edu.astanait.diplomawork.service.serviceInterface.feign.ScopusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/scopus")
public class ScopusController {

    private final ScopusService scopusService;

    @Autowired
    public ScopusController(ScopusService scopusService) {
        this.scopusService = scopusService;
    }

    @GetMapping ("/get/information/id/{id}")
    public ResponseEntity<HttpStatus> getScopusInformation(@PathVariable(name = "id") Long id) {
        this.scopusService.getScopusInformation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}