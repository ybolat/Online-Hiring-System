package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ProjectDtoRequest {

    @NotNull(message = "Пользователь не был указан.")
    private Long userId;

    @NotNull(message = "Дата начала не была указана.")
    private LocalDate startedDate;

    @NotNull(message = "Дата окончания не была указана.")
    private LocalDate finishedDate;

    @NotNull(message = "Сум не был указан.")
    private Float sum;

    @NotNull(message = "Фунд не был указан.")
    private String fund;

    @NotNull(message = "Проект не был указан.")
    private Long projectId;
}
