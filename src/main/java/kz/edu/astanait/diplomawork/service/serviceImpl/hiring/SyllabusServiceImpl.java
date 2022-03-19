package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Syllabus;
import kz.edu.astanait.diplomawork.repository.hiring.SyllabusRepository;
import kz.edu.astanait.diplomawork.service.serviceImpl.catalog.SubjectServiceImpl;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class SyllabusServiceImpl implements SyllabusService {

    private final SyllabusRepository syllabusRepository;

    private final UserProfessionalInfoService userProfessionalInfoService;
    private final SubjectServiceImpl subjectService;

    @Autowired
    public SyllabusServiceImpl(SyllabusRepository syllabusRepository, UserProfessionalInfoService userProfessionalInfoService, SubjectServiceImpl subjectService) {
        this.syllabusRepository = syllabusRepository;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.subjectService = subjectService;
    }

    @Override
    public List<Syllabus> getAllByUserId(Long id) {
        return this.syllabusRepository.findAllByUserProfessionalInfoId(id);
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

        syllabus.setUserProfessionalInfo(this.userProfessionalInfoService.getByIdThrowException(syllabusDtoRequest.getUserProfessionalInfoId()));
        syllabus.setSubject(this.subjectService.getByIdThrowException(syllabusDtoRequest.getSubjectId()));

        try{
            this.syllabusRepository.save(syllabus);
        } catch (Exception e) {
            log.error(e);
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
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "deleting", "syllabus"));
        }
    }
}
