package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ProjectDtoRequest {

    @NotNull(message = "Название не была указана.")
    private String projectName;

    @NotNull(message = "Дата начала не была указана.")
    private LocalDate startedDate;

    @NotNull(message = "Дата окончания не была указана.")
    private LocalDate finishedDate;

    @NotNull(message = "Роль не была указан.")
    private String role;

    @NotNull(message = "Сум не был указан.")
    private Float sum;

    @NotNull(message = "Фунд не был указан.")
    private String fund;

    @NotNull(message = "Тип проекта не был указан.")
    private Long projectTypeId;
}
