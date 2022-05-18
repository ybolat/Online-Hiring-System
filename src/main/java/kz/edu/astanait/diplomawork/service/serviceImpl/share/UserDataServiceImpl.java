package kz.edu.astanait.diplomawork.service.serviceImpl.share;

import kz.edu.astanait.diplomawork.dto.responseDto.share.UserDataDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicDegreeMapper;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicTitleMapper;
import kz.edu.astanait.diplomawork.mapper.catalog.PositionMapper;
import kz.edu.astanait.diplomawork.mapper.hiring.*;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserService userService;
    private final UserProfessionalInfoService userProfessionalInfoService;
    private final ArticleService articleService;
    private final DevelopmentService developmentService;
    private final ProjectService projectService;
    private final UserDocumentService userDocumentService;
    private final IntelligenceLegalDocumentService intelligenceLegalDocumentService;
    private final CertificateService certificateService;

    @Autowired
    public UserDataServiceImpl(UserService userService, UserProfessionalInfoService userProfessionalInfoService, ArticleService articleService, DevelopmentService developmentService, ProjectService projectService, UserDocumentService userDocumentService, IntelligenceLegalDocumentService intelligenceLegalDocumentService, CertificateService certificateService) {
        this.userService = userService;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.articleService = articleService;
        this.developmentService = developmentService;
        this.projectService = projectService;
        this.userDocumentService = userDocumentService;
        this.intelligenceLegalDocumentService = intelligenceLegalDocumentService;
        this.certificateService = certificateService;
    }

    @Override
    public UserDataDtoResponse getData(String secretKey, Long id) {
        UserDataDtoResponse userDataDtoResponse = new UserDataDtoResponse();

        User user = this.userService.getByIdThrowException(id);
        UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(id);
        List<Article> articleList = this.articleService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());
        List<Development> developmentList = this.developmentService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());
        List<Project> projectList = this.projectService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());
        UserDocument userDocument = this.userDocumentService.getByUserIdThrowException(id);
        List<IntelligenceLegalDocument> intelligenceLegalDocuments = this.intelligenceLegalDocumentService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());
        List<Certificate> certificates = this.certificateService.getAllByUserProfessionalInfoId(userProfessionalInfo.getId());

        userDataDtoResponse.setLastname(user.getLastname());
        userDataDtoResponse.setName(user.getName());
        if (Strings.isNotBlank(user.getPatronymic())) userDataDtoResponse.setPatronymic(user.getPatronymic());
        userDataDtoResponse.setPhoto(DocumentMapper.documentsToDto(userDocument.getPhoto()));
        userDataDtoResponse.setPositionDtoResponse(PositionMapper.positionToDto(userProfessionalInfo.getVacancy().getPosition()));
        userDataDtoResponse.setAcademicDegreeDtoResponse(AcademicDegreeMapper.academicDegreeToDto(userProfessionalInfo.getAcademicDegree()));
        userDataDtoResponse.setAcademicTitleDtoResponse(AcademicTitleMapper.academicTitleToDto(userProfessionalInfo.getAcademicTitle()));
        userDataDtoResponse.setScopusId(userProfessionalInfo.getScopus());
        userDataDtoResponse.setScopusHIndex(userProfessionalInfo.getScopusHIndex());
        userDataDtoResponse.setScopusLink(userProfessionalInfo.getScopusLink());
        userDataDtoResponse.setResearchId(userProfessionalInfo.getResearch());
        userDataDtoResponse.setResearchLink(userProfessionalInfo.getResearchLink());
        userDataDtoResponse.setResearchHIndex(userProfessionalInfo.getResearchHIndex());
        userDataDtoResponse.setGoogleScholarLing(userProfessionalInfo.getGoogleScholar());
        userDataDtoResponse.setGoogleScholarHIndex(userProfessionalInfo.getGoogleScholarHIndex());
        userDataDtoResponse.setOrcidLink(userProfessionalInfo.getOrcid());
        userDataDtoResponse.setExperience(userProfessionalInfo.getExperience());
        userDataDtoResponse.setScientificInterests(userProfessionalInfo.getScientificInterests());
        userDataDtoResponse.setProjectDtoResponse(projectList.stream().map(ProjectMapper::projectToDto).collect(Collectors.toList()));
        userDataDtoResponse.setArticleDtoResponses(articleList.stream().map(ArticleMapper::articleToDto).collect(Collectors.toList()));
        userDataDtoResponse.setDevelopmentDtoResponse(developmentList.stream().map(DevelopmentMapper::developmentToDto).collect(Collectors.toList()));
        userDataDtoResponse.setIntelligenceLegalDocumentDtoResponse(intelligenceLegalDocuments.stream().map(IntelligenceLegalDocumentMapper::intelligenceLegalDocumentToDto).collect(Collectors.toList()));
        userDataDtoResponse.setEmail(user.getEmail());
        userDataDtoResponse.setPhone(user.getPhone());
        userDataDtoResponse.setCertificateDtoResponseList(certificates.stream().map(CertificateMapper::certificateToDto).collect(Collectors.toList()));
        userDataDtoResponse.setCv(DocumentMapper.documentsToDto(userDocument.getCv()));
        return null;
    }
}
