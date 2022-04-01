package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserProfessionalInfoDtoRequest {

    @NotNull(message = "Пользователь не был указан.")
    private Long userId;

    @NotNull(message = "Вакансия не была указана.")
    private Long vacancyId;

    @NotNull(message =  "Академическая степень не была указано.")
    private Long academicDegreeId;

    private Long academicTitleId;

    private String scopus;

    private String research;

    private String googleScholar;

    private String orcid;

    private String experience;

    private String scientificInterests;

    private String education;
}
