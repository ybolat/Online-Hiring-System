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

    private String descriptionEn;

    private String descriptionRu;

    private String descriptionKz;

    @NotNull(message = "Код не был указан.")
    private String code;

    @NotNull(message = "Количество кредитов не было указано.")
    private Integer volumeCredits;

    @NotNull(message = "Академическая степень не была указана.")
    private Long academicDegreeId;
}
