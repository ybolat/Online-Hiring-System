package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusByWeekDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.InvalidData;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Syllabus;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import kz.edu.astanait.diplomawork.repository.hiring.SyllabusRepository;
import kz.edu.astanait.diplomawork.service.serviceImpl.catalog.SubjectServiceImpl;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusByWeekService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
@Log4j2
public class SyllabusServiceImpl implements SyllabusService {

    private final SyllabusRepository syllabusRepository;

    private final UserProfessionalInfoService userProfessionalInfoService;
    private final SubjectServiceImpl subjectService;
    private final SyllabusByWeekService syllabusByWeekService;
    private final UserService userService;

    @Autowired
    public SyllabusServiceImpl(SyllabusRepository syllabusRepository, UserProfessionalInfoService userProfessionalInfoService, SubjectServiceImpl subjectService, @Lazy SyllabusByWeekService syllabusByWeekService, UserService userService) {
        this.syllabusRepository = syllabusRepository;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.subjectService = subjectService;
        this.syllabusByWeekService = syllabusByWeekService;
        this.userService = userService;
    }

    @Override
    public List<Syllabus> getAllByUserProfessionalInfoId(Long id) {
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
    public List<Syllabus> getMySyllabuses(Principal principal) {
        return this.syllabusRepository.findByUserEmail(principal.getName());
    }

    @Override
    public void create(SyllabusDtoRequest syllabusDtoRequest, Principal principal) {
        Syllabus syllabus = new Syllabus();

        User user = this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName());

        syllabus.setUserProfessionalInfo(this.userProfessionalInfoService.getByUserIdThrowException(user.getId()));
        syllabus.setSubject(this.subjectService.getByIdThrowException(syllabusDtoRequest.getSubjectId()));

        try{
            this.syllabusRepository.save(syllabus);
        } catch (Exception e) {
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "creating" , "syllabus"));
        }
    }

    @Override
    public void update(SyllabusDtoRequest syllabusDtoRequest, Long id, HashMap<Long, SyllabusByWeekDtoRequest> syllabusByWeekDtoRequestHashMap) {
        this.syllabusByWeekService.update(syllabusByWeekDtoRequestHashMap);
        Syllabus syllabus = this.getByIdThrowException(id);

        syllabus.setSubject(this.subjectService.getByIdThrowException(syllabusDtoRequest.getSubjectId()));

        try{
            this.syllabusRepository.save(syllabus);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "syllabus"));
        }
    }

    @Override
    public void delete(Long id) {
        this.syllabusByWeekService.delete(id);
        Syllabus syllabus = this.getByIdThrowException(id);

        try{
            this.syllabusRepository.delete(syllabus);
        } catch (Exception e) {
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "deleting", "syllabus"));
        }
    }

    @Override
    public void createAll(List<SyllabusDtoRequest> syllabusDtoRequestList, Principal principal){
        if (syllabusDtoRequestList.size() == 0) throw new InvalidData(ExceptionDescription.InvalidDataException);

        List<Syllabus> syllabusList = new ArrayList<>();

        User user = this.userService.getByEmailThrowException(principal.getName());
        UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(user.getId());

        for(SyllabusDtoRequest syllabusDtoRequest: syllabusDtoRequestList){
            Syllabus syllabus = new Syllabus();

            syllabus.setUserProfessionalInfo(userProfessionalInfo);
            if(Objects.nonNull(syllabusDtoRequest.getSubjectId())) syllabus.setSubject(this.subjectService.getByIdThrowException(syllabusDtoRequest.getSubjectId()));

            syllabusList.add(syllabus);

            try{
                this.syllabusRepository.saveAll(syllabusList);
            }catch (Exception e){
                log.error(e);
                throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating","syllabusList"));
            }
        }

    }
}
