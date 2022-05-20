package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MeetingDtoRequest {

    @NotNull(message = "Ссылка не была указана.")
    private String meetingLink;

    @NotNull(message = "Название не была не указана.")
    private String meetingTitle;

    @NotNull(message = "Описание не была указана.")
    private String meetingDescription;

    @NotNull(message = "Начала собрания не было указано.")
    private String startDateTime;

    @NotNull(message = "Конец собрания не было указано.")
    private String endDateTime;
}
