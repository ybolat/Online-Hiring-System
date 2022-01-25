package kz.edu.astanait.diplomawork.dto.responseDto;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicDegreeDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.security.RoleDtoResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDtoResponse {

    private Long id;

    private RoleDtoResponse role;

    private String email;

    private String name;

    private String lastname;

    private String patronymic;

    private String position;

    private String phone;

    private LocalDateTime createdDate;

    private AcademicDegreeDtoResponse academicDegree;

    private String academicTitle;

    private String scopus;

    private String research;

    private String googleScholar;

    private String orcid;

    private String experience;

    private String scientificInterests;

    private String education;

}
