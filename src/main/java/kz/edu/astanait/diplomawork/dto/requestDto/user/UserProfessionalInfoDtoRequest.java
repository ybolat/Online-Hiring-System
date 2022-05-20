package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserProfessionalInfoDtoRequest {

    @NotNull(message = "Вакансия не была указана.")
    private Long vacancyId;

    @NotNull(message =  "Академическая степень не была указано.")
    private Long academicDegreeId;

    private Long academicTitleId;

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
