package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.ProjectTypeDtoResponse;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProjectDtoResponse {

    private Long id;

    private UserDtoResponse user;

    private LocalDate startedDate;

    private LocalDate finishedDate;

    private Float sum;

    private String fund;

    private ProjectTypeDtoResponse projectType;
}
