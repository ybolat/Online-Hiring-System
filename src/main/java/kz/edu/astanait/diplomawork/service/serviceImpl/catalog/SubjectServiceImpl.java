package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.SubjectDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.Subject;
import kz.edu.astanait.diplomawork.mapper.catalog.SubjectsMapper;
import kz.edu.astanait.diplomawork.repository.catalog.SubjectRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    @Override
    public List<SubjectDtoResponse> getAllSubjectDto() {
        return getAllSubject().stream().map(SubjectsMapper::subjectToDto).collect(Collectors.toList());
    }
}
