package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SyllabusByWeekDtoRequest {

    @NotNull(message = "Силлабус не был указан.")
    private Long syllabusId;

    @NotNull(message = "Номер недели не был указан.")
    private Integer weekNumber;

    @NotNull(message = "Название не было указано.")
    private String title;

    @NotNull(message = "Описание не было указано.")
    private String description;
}
