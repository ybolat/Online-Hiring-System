package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class VacancyDtoRequest {

    @NotNull(message = "The department is not specified.")
    private Long departmentId;

    @NotNull(message = "The academic title is not specified.")
    private Long academicTitleId;

    @NotNull(message = "The position is not specified.")
    private Long positionId;

    @NotNull(message = "The link is not specified.")
    private String link_directory;

    @NotNull(message = "The start date is not specified.")
    private LocalDateTime start_date;

    @NotNull(message = "The finish date is not specified.")
    private LocalDateTime finish_date;

    @NotNull(message = "The number is not specified.")
    private Long number;
}
