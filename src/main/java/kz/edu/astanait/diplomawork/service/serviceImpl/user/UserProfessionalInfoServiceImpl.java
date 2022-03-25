package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserProfessionalInfoDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import kz.edu.astanait.diplomawork.repository.user.UserProfessionalInfoRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicDegreeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicTitleService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.VacancyService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public UserProfessionalInfoServiceImpl(UserProfessionalInfoRepository userProfessionalInfoRepository, UserService userService, UserService userService1, VacancyService vacancyService, AcademicDegreeService academicDegreeService, AcademicTitleService academicTitleService) {
        this.userProfessionalInfoRepository = userProfessionalInfoRepository;
        this.userService = userService1;
        this.vacancyService = vacancyService;
        this.academicDegreeService = academicDegreeService;
        this.academicTitleService = academicTitleService;
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
    public void createProfile(UserProfessionalInfoDtoRequest userProfessionalInfoDtoRequest) {
        UserProfessionalInfo userProfessionalInfo = new UserProfessionalInfo();

        userProfessionalInfo.setUser(this.userService.getByIdThrowException(userProfessionalInfoDtoRequest.getUserId()));
        userProfessionalInfo.setVacancyId(this.vacancyService.getByIdThrowException(userProfessionalInfoDtoRequest.getVacancyId()));
        userProfessionalInfo.setAcademicDegree(this.academicDegreeService.getByIdThrowException(userProfessionalInfoDtoRequest.getAcademicDegreeId()));
        userProfessionalInfo.setAcademicTitle(this.academicTitleService.getByIdThrowException(userProfessionalInfoDtoRequest.getAcademicTitleId()));
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getScopus())) userProfessionalInfo.setScopus(userProfessionalInfoDtoRequest.getScopus());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getResearch())) userProfessionalInfo.setResearch(userProfessionalInfoDtoRequest.getResearch());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getGoogleScholar())) userProfessionalInfo.setGoogleScholar(userProfessionalInfoDtoRequest.getGoogleScholar());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getOrcid())) userProfessionalInfo.setOrcid(userProfessionalInfoDtoRequest.getOrcid());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getExperience())) userProfessionalInfo.setExperience(userProfessionalInfoDtoRequest.getExperience());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getScientificInterests())) userProfessionalInfo.setScientificInterests(userProfessionalInfoDtoRequest.getScientificInterests());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getEducation())) userProfessionalInfo.setEducation(userProfessionalInfoDtoRequest.getEducation());

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

        if(Objects.nonNull(userProfessionalInfoDtoRequest.getVacancyId())) userProfessionalInfo.setVacancyId(this.vacancyService.getByIdThrowException(userProfessionalInfoDtoRequest.getVacancyId()));
        if(Objects.nonNull(userProfessionalInfoDtoRequest.getAcademicDegreeId())) userProfessionalInfo.setAcademicDegree(this.academicDegreeService.getByIdThrowException(userProfessionalInfoDtoRequest.getAcademicDegreeId()));
        if(Objects.nonNull(userProfessionalInfoDtoRequest.getAcademicTitleId())) userProfessionalInfo.setAcademicTitle(this.academicTitleService.getByIdThrowException(userProfessionalInfoDtoRequest.getAcademicTitleId()));
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getScopus())) userProfessionalInfo.setScopus(userProfessionalInfoDtoRequest.getScopus());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getResearch())) userProfessionalInfo.setResearch(userProfessionalInfoDtoRequest.getResearch());
        if(Strings.isNotBlank(userProfessionalInfoDtoRequest.getGoogleScholar())) userProfessionalInfo.setGoogleScholar(userProfessionalInfoDtoRequest.getGoogleScholar());
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
