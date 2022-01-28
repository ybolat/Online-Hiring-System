package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Syllabus;
import kz.edu.astanait.diplomawork.repository.hiring.SyllabusRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyllabusServiceImpl implements SyllabusService {

    private final SyllabusRepository syllabusRepository;

    @Autowired
    public SyllabusServiceImpl(SyllabusRepository syllabusRepository) {
        this.syllabusRepository = syllabusRepository;
    }

    @Override
    public List<Syllabus> getAllByUserId(Long id) {
        return syllabusRepository.findAllByUserId(id);
    }
}
