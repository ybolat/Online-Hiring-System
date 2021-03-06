package kz.edu.astanait.diplomawork.dto.responseDto.user;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicDegreeDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicTitleDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.hiring.VacancyDtoResponse;
import lombok.Data;

@Data
public class UserProfessionalInfoDtoResponse {

    private Long id;

//    private UserDtoResponse user;

    private VacancyDtoResponse vacancy;

    private AcademicDegreeDtoResponse academicDegree;

    private AcademicTitleDtoResponse academicTitle;

    private String scopus;

    private Long scopusHIndex;

    private String scopusLink;

    private String research;

    private Long researchHIndex;

    private String researchLink;

    private String googleScholar;

    private Long googleScholarHIndex;

    private String orcid;

    private String experience;

    private String scientificInterests;

    private String education;
}
