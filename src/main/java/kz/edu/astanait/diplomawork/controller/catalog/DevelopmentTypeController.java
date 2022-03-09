package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.DevelopmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/catalog/development-type")
public class DevelopmentTypeController {

    private final DevelopmentTypeService developmentTypeService;

    @Autowired
    public DevelopmentTypeController(DevelopmentTypeService developmentTypeService) {
        this.developmentTypeService = developmentTypeService;
    }
}
