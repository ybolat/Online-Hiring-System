package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.catalog.AcademicDegree;
import kz.edu.astanait.diplomawork.repository.catalog.AcademicDegreeRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicDegreeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AcademicDegreeServiceImpl implements AcademicDegreeService {

    private final AcademicDegreeRepository academicDegreeRepository;

    @Autowired
    public AcademicDegreeServiceImpl(AcademicDegreeRepository academicDegreeRepository) {
        this.academicDegreeRepository = academicDegreeRepository;
    }

    @Override
    public List<AcademicDegree> getAll() {
        return this.academicDegreeRepository.findAll();
    }

    @Override
    public Optional<AcademicDegree> getById(Long id) {
        return this.academicDegreeRepository.findById(id);
    }

    @Override
    public AcademicDegree getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Academic Degree", "id", id)));
    }
}
