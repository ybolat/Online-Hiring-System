package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.model.catalog.AcademicDegree;
import kz.edu.astanait.diplomawork.repository.catalog.AcademicDegreeRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicDegreeServiceImpl implements AcademicDegreeService {

    private final AcademicDegreeRepository academicDegreeRepository;

    @Autowired
    public AcademicDegreeServiceImpl(AcademicDegreeRepository academicDegreeRepository) {
        this.academicDegreeRepository = academicDegreeRepository;
    }

    @Override
    public List<AcademicDegree> getAll() {
        return academicDegreeRepository.findAll();
    }
}
