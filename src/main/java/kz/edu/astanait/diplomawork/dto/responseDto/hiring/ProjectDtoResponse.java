package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.UserDtoResponse;
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

    private String type;

    private ProjectTypeDtoResponse projectType;
}
