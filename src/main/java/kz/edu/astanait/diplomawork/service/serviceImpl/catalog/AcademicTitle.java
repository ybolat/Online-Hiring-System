package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.repository.catalog.AcademicTitleRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicTitle implements AcademicTitleService {

    private final AcademicTitleRepository academicTitleRepository;

    @Autowired
    public AcademicTitle(AcademicTitleRepository academicTitleRepository) {
        this.academicTitleRepository = academicTitleRepository;
    }
}
