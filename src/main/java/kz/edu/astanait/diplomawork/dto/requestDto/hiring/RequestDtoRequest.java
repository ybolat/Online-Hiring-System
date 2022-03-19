package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RequestDtoRequest {

    @NotNull(message = "Пользователь не был указан.")
    private Long userId;

    @NotNull(message = "Статус не был указан.")
    private Long statusId;

    @NotNull(message = "Бэкграунд не был указан.")
    private String background;

    @NotNull(message = "Дополнение не было указано.")
    private String additional;
}
