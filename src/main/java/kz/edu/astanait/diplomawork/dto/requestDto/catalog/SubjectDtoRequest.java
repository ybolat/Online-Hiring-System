package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubjectDtoRequest {

    @NotNull(message = "The title in English was not specified.")
    private String titleEn;

    @NotNull(message = "The title in Russian was not specified.")
    private String titleRu;

    @NotNull(message = "The title in Kazakh was not specified.")
    private String titleKz;

    private String descriptionEn;

    private String descriptionRu;

    private String descriptionKz;

    @NotNull(message = "The code was not specified.")
    private String code;

    @NotNull(message = "The volume of credits was not specified.")
    private Integer volumeCredits;

    @NotNull(message = "The academic degree id was not specified.")
    private Long academicDegreeId;
}
