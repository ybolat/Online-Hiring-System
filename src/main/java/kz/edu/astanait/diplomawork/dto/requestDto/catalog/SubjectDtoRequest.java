package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubjectDtoRequest {

    @NotNull(message = "Название на английском не было указано.")
    private String titleEn;

    @NotNull(message = "Название на русском не было указано.")
    private String titleRu;

    @NotNull(message = "Название на казахском не было указано.")
    private String titleKz;

    @NotNull(message = "Описание на английском не было указано.")
    private String descriptionEn;

    @NotNull(message = "Описание на русском не было указано.")
    private String descriptionRu;

    @NotNull(message = "Описание на казахстком не было указано.")
    private String descriptionKz;

    @NotNull(message = "Код не был указан.")
    private String code;

    @NotNull(message = "Количество кредитов не было указано.")
    private Integer volumeCredits;

    @NotNull(message = "Академическая степень не была указана.")
    private Long academicDegreeId;
}
