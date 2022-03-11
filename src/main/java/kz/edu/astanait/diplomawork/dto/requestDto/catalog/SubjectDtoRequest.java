package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubjectDtoRequest {

    @NotNull(message = "Название на английском не была указана.")
    private String titleEn;

    @NotNull(message = "Название на русском не был указан.")
    private String titleRu;

    @NotNull(message = "Название на казахском не был указан.")
    private String titleKz;

    @NotNull(message = "Описание на английском не была указана.")
    private String descriptionEn;

    @NotNull(message = "Описание на русском не был указан.")
    private String descriptionRu;

    @NotNull(message = "Описание на казахстком не был указан.")
    private String descriptionKz;

    @NotNull(message = "Код не был указан.")
    private String code;

    @NotNull(message = "Количество кредитов не было указано.")
    private Integer volumeCredits;

    @NotNull(message = "Академическая степень не было указано.")
    private Long academicDegreeId;
}
