package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import kz.edu.astanait.diplomawork.model.catalog.Status;
import kz.edu.astanait.diplomawork.model.user.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RequestDtoRequest {

    @NotNull(message = "Статус не был указан.")
    private Long statusId;

    @NotNull(message = "Пользователь не был указан.")
    private User user;

    @NotNull(message = "Статус не был указан.")
    private Status status;

    @NotNull(message = "Время создания не была указана.")
    private LocalDateTime createdDate;

    @NotNull(message = "Бэкграунд не был указан.")
    private String background;

    @NotNull(message = "Дополнение не было указано.")
    private String additional;
}
