package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.PublicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hiring/publications")
public class PublicationsController {

    private final PublicationsService publicationsService;

    @Autowired
    public PublicationsController(PublicationsService publicationsService) {
        this.publicationsService = publicationsService;
    }
}
