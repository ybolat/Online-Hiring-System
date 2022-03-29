package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.catalog.AcademicTitle;
import kz.edu.astanait.diplomawork.repository.catalog.AcademicTitleRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicTitleServiceImpl implements AcademicTitleService {

    private final AcademicTitleRepository academicTitleRepository;

    @Autowired
    public AcademicTitleServiceImpl(AcademicTitleRepository academicTitleRepository) {
        this.academicTitleRepository = academicTitleRepository;
    }

    @Override
    public List<AcademicTitle> getAll() {
        return this.academicTitleRepository.findAll();
    }

    @Override
    public Optional<AcademicTitle> getById(Long id) {
        return this.academicTitleRepository.findById(id);
    }

    @Override
    public AcademicTitle getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException(
                        String.format(ExceptionDescription.CustomNotFoundException, "Academic Title", "id", id)));
    }
}
