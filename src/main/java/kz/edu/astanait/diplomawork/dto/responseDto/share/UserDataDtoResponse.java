package kz.edu.astanait.diplomawork.dto.responseDto.share;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicDegreeDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicTitleDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.PositionDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.SubjectDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.*;
import lombok.Data;

import java.util.List;

@Data
public class UserDataDtoResponse {

    private String lastname;

    private String name;

    private String patronymic;

    private DocumentsDtoResponse photo;

    private PositionDtoResponse positionDtoResponse;

    private AcademicDegreeDtoResponse academicDegreeDtoResponse;

    private AcademicTitleDtoResponse academicTitleDtoResponse;

    private String scopusLink;

    private String scopusId;

    private Long scopusHIndex;

    private String researchLink;

    private String researchId;

    private Long researchHIndex;

    private String googleScholarLing;

    private Long googleScholarHIndex;

    private String orcidLink;

    private String experience;

    private List<SubjectDtoResponse> subjectDtoResponseList;

    private String scientificInterests;

    private List<ProjectDtoResponse> projectDtoResponse;

    private List<ArticleDtoResponse> articleDtoResponses;

    private List<DevelopmentDtoResponse> developmentDtoResponse;

    private List<IntelligenceLegalDocumentDtoResponse> intelligenceLegalDocumentDtoResponse;

    private String email;

    private String phone;

    private List<CertificateDtoResponse> certificateDtoResponseList;

    private DocumentsDtoResponse cv;
}
