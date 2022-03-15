package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MeetingDtoRequest {

    @NotNull(message = "Название не было указано.")
    private String name;

    @NotNull(message = "Ссылка не была указана.")
    private String link;

    @NotNull(message = "Дата не была указана.")
    private LocalDateTime dateTime;

    @NotNull(message = "Запрос не был указан.")
    private Long requestId;

    @NotNull(message = "Список комиссий не был указан.")
    private List<Long> commissionIdList;
}
