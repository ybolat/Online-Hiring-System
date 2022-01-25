package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import lombok.Data;

@Data
public class SyllabusByWeekDtoResponse {

    private Long id;

    private SyllabusDtoResponse syllabus;

    private Integer weekNumber;

    private String title;

    private String description;
}
