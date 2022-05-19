package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserProfessionalInfoDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.catalog.Subject;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import kz.edu.astanait.diplomawork.repository.user.UserProfessionalInfoRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicDegreeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicTitleService;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.SubjectService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.VacancyService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class UserProfessionalInfoServiceImpl implements UserProfessionalInfoService {

    private final UserProfessionalInfoRepository userProfessionalInfoRepository;

    private final UserService userService;
    private final VacancyService vacancyService;
    private final AcademicDegreeService academicDegreeService;
    private final AcademicTitleService academicTitleService;
    private final SubjectService subjectService;

    @Autowired
    public UserProfessionalInfoServiceImpl(UserProfessionalInfoRepository userProfessionalInfoRepository, UserService userService, UserService userService1, VacancyService vacancyService, AcademicDegreeService academicDegreeService, AcademicTitleService academicTitleService, SubjectService subjectService) {
        this.userProfessionalInfoRepository = userProfessionalInfoRepository;
        this.userService = userService1;
        this.vacancyService = vacancyService;
        this.academicDegreeService = academicDegreeService;
        this.academicTitleService = academicTitleService;
        this.subjectService = subjectService;
    }

    @Override
    public Optional<UserProfessionalInfo> getById(Long id) {
        return this.userProfessionalInfoRepository.findById(id);
    }

    @Override
    public UserProfessionalInfo getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format(ExceptionDescription.CustomNotFoundException, "User professional info", "id", id)));
    }

    @Override
    public Optional<UserProfessionalInfo> getByUserId(Long id) {
        return this.userProfessionalInfoRepository.findByUserId(id);
    }

    @Override
    public UserProfessionalInfo getByUserIdThrowException(Long id) {
        return this.getByUserId(id).
                orElseThrow(() -> new CustomNotFoundException(String.format(
                        ExceptionDescription.CustomNotFoundException, "User professional info", "User id", id)));
    }

    @Override
    public User getByUserEmailThrowException(String email) {
        return this.userService.getByEmailThrowException(email);
    }

    @Override
    public void createProfile(UserProfessionalInfoDtoRequest userProfessionalInfoDtoRequest, Principal principal) {
        UserProfessionalInfo userProfessionalInfo = new UserProfessionalInfo();
        List<Subject> subjectList = new ArrayList<>();

        if (Objects.nonNull(userProfessionalInfoDtoRequest.getSubjectsId())){
            for (int i = 0; i < userProfessionalInfoDtoRequest.getSubjectsId().size(); i++) {
                Subject subject = this.subjectService.getByIdThrowException(userProfessionalInfoDtoRequest.getSubjectsId().get(i));
                subjectList.add(subject);
            }
        }

        userProfessionalInfo.setUser(this.userService.getByEmailThrowException(principal.getName()));
        userProfessionalInfo.setVacancy(this.vacancyService.getByIdThrowException(userProfessionalInfoDtoRequest.getVacancyId()));
        userProfessionalInfo.setAcademicDegree(this.academicDegreeService.getByIdThrowException(userProfessionalInfoDtoRequest.getAcademicDegreeId()));
        userProfessionalInfo.setAcademicTitle(this.academicTitleService.getByIdThrowException(userProfessionalInfoDtoRequest.getAcademicTitleId()));
        if (Objects.nonNull(userProfessionalInfoDtoRequest.getSubjectsId())) userProfessionalInfo.setSubjectList(subjectList);
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getScopus())) userProfessionalInfo.setScopus(userProfessionalInfoDtoRequest.getScopus());
        if (Objects.nonNull(userProfessionalInfoDtoRequest.getScopusHIndex())) userProfessionalInfo.setScopusHIndex(userProfessionalInfoDtoRequest.getScopusHIndex());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getScopusLink())) userProfessionalInfo.setScopusLink(userProfessionalInfoDtoRequest.getScopusLink());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getResearch())) userProfessionalInfo.setResearch(userProfessionalInfoDtoRequest.getResearch());
        if (Objects.nonNull(userProfessionalInfoDtoRequest.getResearchHIndex())) userProfessionalInfo.setResearchHIndex(userProfessionalInfoDtoRequest.getResearchHIndex());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getResearchLink())) userProfessionalInfo.setResearchLink(userProfessionalInfoDtoRequest.getResearchLink());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getGoogleScholar())) userProfessionalInfo.setGoogleScholar(userProfessionalInfoDtoRequest.getGoogleScholar());
        if (Objects.nonNull(userProfessionalInfoDtoRequest.getGoogleScholarHIndex())) userProfessionalInfo.setGoogleScholarHIndex(userProfessionalInfoDtoRequest.getGoogleScholarHIndex());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getOrcid())) userProfessionalInfo.setOrcid(userProfessionalInfoDtoRequest.getOrcid());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getExperience())) userProfessionalInfo.setExperience(userProfessionalInfoDtoRequest.getExperience());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getScientificInterests())) userProfessionalInfo.setScientificInterests(userProfessionalInfoDtoRequest.getScientificInterests());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getEducation())) userProfessionalInfo.setEducation(userProfessionalInfoDtoRequest.getEducation());

        try {
            this.userProfessionalInfoRepository.save(userProfessionalInfo);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "profile"));
        }
    }

    @Override
    public void updateProfile(UserProfessionalInfoDtoRequest userProfessionalInfoDtoRequest, Long id) {
        UserProfessionalInfo userProfessionalInfo = this.getByIdThrowException(id);

        if(Objects.nonNull(userProfessionalInfoDtoRequest.getVacancyId())) userProfessionalInfo.setVacancy(this.vacancyService.getByIdThrowException(userProfessionalInfoDtoRequest.getVacancyId()));
        if(Objects.nonNull(userProfessionalInfoDtoRequest.getAcademicDegreeId())) userProfessionalInfo.setAcademicDegree(this.academicDegreeService.getByIdThrowException(userProfessionalInfoDtoRequest.getAcademicDegreeId()));
        if(Objects.nonNull(userProfessionalInfoDtoRequest.getAcademicTitleId())) userProfessionalInfo.setAcademicTitle(this.academicTitleService.getByIdThrowException(userProfessionalInfoDtoRequest.getAcademicTitleId()));
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getScopus())) userProfessionalInfo.setScopus(userProfessionalInfoDtoRequest.getScopus());
        if (Objects.nonNull(userProfessionalInfoDtoRequest.getScopusHIndex())) userProfessionalInfo.setScopusHIndex(userProfessionalInfoDtoRequest.getScopusHIndex());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getScopusLink())) userProfessionalInfo.setScopusLink(userProfessionalInfoDtoRequest.getScopusLink());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getResearch())) userProfessionalInfo.setResearch(userProfessionalInfoDtoRequest.getResearch());
        if (Objects.nonNull(userProfessionalInfoDtoRequest.getResearchHIndex())) userProfessionalInfo.setResearchHIndex(userProfessionalInfoDtoRequest.getResearchHIndex());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getResearchLink())) userProfessionalInfo.setResearchLink(userProfessionalInfoDtoRequest.getResearchLink());
        if (Strings.isNotBlank(userProfessionalInfoDtoRequest.getGoogleScholar())) userProfessionalInfo.setGoogleScholar(userProfessionalInfoDtoRequest.getGoogleScholar());
        if (Objects.nonNull(userProfessionalInfoDtoRequest.getGoogleScholarHIndex())) userProfessionalInfo.setGoogleScholarHIndex(userProfessionalInfoDtoRequest.getGoogleScholarHIndex());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getOrcid())) userProfessionalInfo.setOrcid(userProfessionalInfoDtoRequest.getOrcid());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getExperience())) userProfessionalInfo.setExperience(userProfessionalInfoDtoRequest.getExperience());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getScientificInterests())) userProfessionalInfo.setScientificInterests(userProfessionalInfoDtoRequest.getScientificInterests());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getEducation())) userProfessionalInfo.setEducation(userProfessionalInfoDtoRequest.getEducation());

        try {
            this.userProfessionalInfoRepository.save(userProfessionalInfo);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "profile"));
        }
    }
}
