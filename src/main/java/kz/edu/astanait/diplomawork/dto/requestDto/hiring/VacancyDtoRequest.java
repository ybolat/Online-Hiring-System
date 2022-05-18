package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class VacancyDtoRequest {

    @NotNull(message = "Департамент не был указан.")
    private Long departmentId;

    @NotNull(message = "Академическая степень не была указана.")
    private Long academicTitleId;

    @NotNull(message = "Позиция не была указана.")
    private Long positionId;

    @NotNull(message = "Ссылка не была указана.")
    private String link_directory;

    @NotNull(message = "Дата начала не была указана.")
    private LocalDateTime start_date;

    @NotNull(message = "Дата окончания не была указана.")
    private LocalDateTime finish_date;

    @NotNull(message = "Количество не было указано.")
    private Long number;
}
