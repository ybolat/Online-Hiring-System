package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.hiring.Syllabus;
import kz.edu.astanait.diplomawork.repository.hiring.SyllabusRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SyllabusServiceImpl implements SyllabusService {

    private final SyllabusRepository syllabusRepository;

    @Autowired
    public SyllabusServiceImpl(SyllabusRepository syllabusRepository) {
        this.syllabusRepository = syllabusRepository;
    }

    @Override
    public List<Syllabus> getAllByUserId(Long id) {
        return this.syllabusRepository.findAllByUserId(id);
    }

    @Override
    public Optional<Syllabus> getById(Long id) {
        return this.syllabusRepository.findById(id);
    }

    @Override
    public Syllabus getByIdWithException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Syllabus", "id", id)));
    }
}
