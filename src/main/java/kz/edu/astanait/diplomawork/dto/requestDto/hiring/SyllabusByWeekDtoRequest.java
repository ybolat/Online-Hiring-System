package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SyllabusByWeekDtoRequest {

    @NotNull(message = "The syllabus is not specified.")
    private Long syllabusId;

    @NotNull(message = "The week number is not specified.")
    private Integer weekNumber;

    @NotNull(message = "The title is not specified.")
    private String title;

    @NotNull(message = "The description is not specified.")
    private String description;
}
