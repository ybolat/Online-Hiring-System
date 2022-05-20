package kz.edu.astanait.diplomawork.service.serviceImpl.share;

import kz.edu.astanait.diplomawork.dto.responseDto.share.UserDataDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicDegreeMapper;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicTitleMapper;
import kz.edu.astanait.diplomawork.mapper.catalog.PositionMapper;
import kz.edu.astanait.diplomawork.mapper.catalog.SubjectMapper;
import kz.edu.astanait.diplomawork.mapper.hiring.*;
import kz.edu.astanait.diplomawork.model.catalog.Subject;
import kz.edu.astanait.diplomawork.model.hiring.*;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.model.user.UserDocument;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.*;
import kz.edu.astanait.diplomawork.service.serviceInterface.share.UserDataService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserDocumentService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Value("${share.secretKey}")
    private String secretKey;

    private final UserService userService;
    private final UserProfessionalInfoService userProfessionalInfoService;
    private final ArticleService articleService;
    private final DevelopmentService developmentService;
    private final ProjectService projectService;
    private final UserDocumentService userDocumentService;
    private final IntelligenceLegalDocumentService intelligenceLegalDocumentService;
    private final CertificateService certificateService;
    private final SyllabusService syllabusService;

    @Autowired
    public UserDataServiceImpl(UserService userService, UserProfessionalInfoService userProfessionalInfoService, ArticleService articleService, DevelopmentService developmentService, ProjectService projectService, UserDocumentService userDocumentService, IntelligenceLegalDocumentService intelligenceLegalDocumentService, CertificateService certificateService, SyllabusService syllabusService) {
        this.userService = userService;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.articleService = articleService;
        this.developmentService = developmentService;
        this.projectService = projectService;
        this.userDocumentService = userDocumentService;
        this.intelligenceLegalDocumentService = intelligenceLegalDocumentService;
        this.certificateService = certificateService;
        this.syllabusService = syllabusService;
    }

    @Override
    public UserDataDtoResponse getData(String secretKeyCheck, Long id) {
        UserDataDtoResponse userDataDtoResponse = new UserDataDtoResponse();

        if (secretKeyCheck.equals(this.secretKey)) {
            User user = this.userService.getByIdThrowException(id);
            UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(id);
            List<Article> articleList = this.articleService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());
            List<Development> developmentList = this.developmentService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());
            List<Project> projectList = this.projectService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());
            UserDocument userDocument = this.userDocumentService.getByUserIdThrowException(id);
            List<IntelligenceLegalDocument> intelligenceLegalDocuments = this.intelligenceLegalDocumentService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());
            List<Certificate> certificates = this.certificateService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());
            List<Syllabus> syllabusList = this.syllabusService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());

            List<Subject> subjectList = new ArrayList<>();

            for (Syllabus syllabus : syllabusList) {
                subjectList.add(syllabus.getSubject());
            }

            userDataDtoResponse.setLastname(user.getLastname());
            userDataDtoResponse.setName(user.getName());
            if (Strings.isNotBlank(user.getPatronymic())) userDataDtoResponse.setPatronymic(user.getPatronymic());
            userDataDtoResponse.setPhoto(DocumentMapper.documentsToDto(userDocument.getPhoto()));
            userDataDtoResponse.setPositionDtoResponse(PositionMapper.positionToDto(userProfessionalInfo.getVacancy().getPosition()));
            userDataDtoResponse.setAcademicDegreeDtoResponse(AcademicDegreeMapper.academicDegreeToDto(userProfessionalInfo.getAcademicDegree()));
            if (Objects.nonNull(userProfessionalInfo.getAcademicTitle())) userDataDtoResponse.setAcademicTitleDtoResponse(AcademicTitleMapper.academicTitleToDto(userProfessionalInfo.getAcademicTitle()));
            if (Strings.isNotBlank(userProfessionalInfo.getScopus())) userDataDtoResponse.setScopusId(userProfessionalInfo.getScopus());
            if (Objects.nonNull(userProfessionalInfo.getScopusHIndex())) userDataDtoResponse.setScopusHIndex(userProfessionalInfo.getScopusHIndex());
            if (Strings.isNotBlank(userProfessionalInfo.getScopusLink())) userDataDtoResponse.setScopusLink(userProfessionalInfo.getScopusLink());
            if (Strings.isNotBlank(userProfessionalInfo.getResearch())) userDataDtoResponse.setResearchId(userProfessionalInfo.getResearch());
            if (Strings.isNotBlank(userProfessionalInfo.getResearchLink())) userDataDtoResponse.setResearchLink(userProfessionalInfo.getResearchLink());
            if (Objects.nonNull(userProfessionalInfo.getResearchHIndex())) userDataDtoResponse.setResearchHIndex(userProfessionalInfo.getResearchHIndex());
            if (Strings.isNotBlank(userProfessionalInfo.getGoogleScholar())) userDataDtoResponse.setGoogleScholarLing(userProfessionalInfo.getGoogleScholar());
            if (Objects.nonNull(userProfessionalInfo.getGoogleScholarHIndex())) userDataDtoResponse.setGoogleScholarHIndex(userProfessionalInfo.getGoogleScholarHIndex());
            userDataDtoResponse.setOrcidLink(userProfessionalInfo.getOrcid());
            userDataDtoResponse.setExperience(userProfessionalInfo.getExperience());
            userDataDtoResponse.setSubjectDtoResponseList(subjectList.stream().map(SubjectMapper::subjectToDto).collect(Collectors.toList()));
            userDataDtoResponse.setScientificInterests(userProfessionalInfo.getScientificInterests());
            if (Objects.nonNull(projectList)) userDataDtoResponse.setProjectDtoResponse(projectList.stream().map(ProjectMapper::projectToDto).collect(Collectors.toList()));
            if (Objects.nonNull(articleList)) userDataDtoResponse.setArticleDtoResponses(articleList.stream().map(ArticleMapper::articleToDto).collect(Collectors.toList()));
            if (Objects.nonNull(developmentList)) userDataDtoResponse.setDevelopmentDtoResponse(developmentList.stream().map(DevelopmentMapper::developmentToDto).collect(Collectors.toList()));
            if (Objects.nonNull(intelligenceLegalDocuments)) userDataDtoResponse.setIntelligenceLegalDocumentDtoResponse(intelligenceLegalDocuments.stream().map(IntelligenceLegalDocumentMapper::intelligenceLegalDocumentToDto).collect(Collectors.toList()));
            userDataDtoResponse.setEmail(user.getEmail());
            userDataDtoResponse.setPhone(user.getPhone());
            if (Objects.nonNull(certificates)) userDataDtoResponse.setCertificateDtoResponseList(certificates.stream().map(CertificateMapper::certificateToDto).collect(Collectors.toList()));
            userDataDtoResponse.setCv(DocumentMapper.documentsToDto(userDocument.getCv()));
        }
        return userDataDtoResponse;
    }
}
