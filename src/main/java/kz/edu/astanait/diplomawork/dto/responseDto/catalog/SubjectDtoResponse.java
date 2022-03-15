package kz.edu.astanait.diplomawork.dto.responseDto.catalog;

import lombok.Data;

@Data
public class SubjectDtoResponse {

    private Long id;

    private String titleEn;

    private String titleRu;

    private String titleKz;

    private String descriptionEn;

    private String descriptionRu;

    private String descriptionKz;

    private String code;

    private Integer volumeCredits;

    private AcademicDegreeDtoResponse academicDegree;
}
