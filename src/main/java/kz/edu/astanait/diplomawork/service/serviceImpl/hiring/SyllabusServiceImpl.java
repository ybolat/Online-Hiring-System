package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.repository.hiring.SyllabusRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyllabusServiceImpl implements SyllabusService {

    private final SyllabusRepository syllabusRepository;

    @Autowired
    public SyllabusServiceImpl(SyllabusRepository syllabusRepository) {
        this.syllabusRepository = syllabusRepository;
    }
}
