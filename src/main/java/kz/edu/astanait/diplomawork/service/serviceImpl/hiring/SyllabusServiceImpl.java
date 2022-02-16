package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Syllabus;
import kz.edu.astanait.diplomawork.repository.hiring.SyllabusRepository;
import kz.edu.astanait.diplomawork.service.serviceImpl.catalog.SubjectServiceImpl;
import kz.edu.astanait.diplomawork.service.serviceImpl.user.UserServiceImpl;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SyllabusServiceImpl implements SyllabusService {

    private final SyllabusRepository syllabusRepository;

    private final UserServiceImpl userService;

    private final SubjectServiceImpl subjectService;

    @Autowired
    public SyllabusServiceImpl(SyllabusRepository syllabusRepository, UserServiceImpl userService, SubjectServiceImpl subjectService) {
        this.syllabusRepository = syllabusRepository;
        this.userService = userService;
        this.subjectService = subjectService;
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
    public Syllabus getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Syllabus", "id", id)));
    }

    @Override
    public void create(SyllabusDtoRequest syllabusDtoRequest) {
        Syllabus syllabus = new Syllabus();

        syllabus.setUser(this.userService.getByIdThrowException(syllabusDtoRequest.getUserId()));
        syllabus.setSubject(this.subjectService.getByIdThrowException(syllabusDtoRequest.getSubjectId()));

        try{
            this.syllabusRepository.save(syllabus);
        } catch (Exception e) {
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "creating" , "syllabus"));
        }
    }

//    @Override
//    public void update(SyllabusDtoRequest syllabusDtoRequest, Long id) {
//        Syllabus syllabus = this.getByIdThrowException(syllabusDtoRequest.getSubjectId());
//
//
//    }

    @Override
    public void delete(Long id) {
        Syllabus syllabus = this.getByIdThrowException(id);

        try{
            this.syllabusRepository.delete(syllabus);
        } catch (Exception e) {
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "deleting", "syllabus"));
        }
    }
}
