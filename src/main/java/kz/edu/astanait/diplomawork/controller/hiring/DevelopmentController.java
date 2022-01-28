package kz.edu.astanait.diplomawork.controller.hiring;

import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hiring/development")
public class DevelopmentController {

    private final DevelopmentService developmentService;

    @Autowired
    public DevelopmentController(DevelopmentService developmentService) {
        this.developmentService = developmentService;
    }
}
