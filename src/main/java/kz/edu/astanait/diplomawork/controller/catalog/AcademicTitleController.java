package kz.edu.astanait.diplomawork.controller.catalog;

import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/catalog/academic-title")
public class AcademicTitleController {

    private final AcademicTitleService academicTitleService;

    @Autowired
    public AcademicTitleController(AcademicTitleService academicTitleService) {
        this.academicTitleService = academicTitleService;
    }
}
