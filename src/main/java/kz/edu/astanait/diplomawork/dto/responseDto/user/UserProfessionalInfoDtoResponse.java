package kz.edu.astanait.diplomawork.dto.responseDto.user;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicDegreeDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicTitleDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.AcademicTitle;
import lombok.Data;

@Data
public class UserProfessionalInfoDtoResponse {

    private Long id;

    private UserDtoResponse user;

    private AcademicDegreeDtoResponse academicDegree;

    private AcademicTitleDtoResponse academicTitle;

    private String scopus;

    private String research;

    private String googleScholar;

    private String orcid;

    private String experience;

    private String scientificInterests;

    private String education;
}
