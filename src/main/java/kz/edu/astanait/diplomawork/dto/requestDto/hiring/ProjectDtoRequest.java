package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ProjectDtoRequest {

    @NotNull(message = "The project name was not specified.")
    private String projectName;

    @NotNull(message = "The start date was not specified.")
    private LocalDate startedDate;

    @NotNull(message = "The finish date is not specified.")
    private LocalDate finishedDate;

    @NotNull(message = "The eole was not specified.")
    private String role;

    @NotNull(message = "The sum was not specified.")
    private Float sum;

    @NotNull(message = "The fund was not specified.")
    private String fund;

    @NotNull(message = "The project type is not specified.")
    private Long projectTypeId;
}
