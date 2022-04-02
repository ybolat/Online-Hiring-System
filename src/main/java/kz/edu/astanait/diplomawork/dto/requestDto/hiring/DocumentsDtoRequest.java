package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DocumentsDtoRequest {

    @NotNull(message = "Документ не был отправлен.")
    private String document;

    @NotNull(message = "Название документа не было указано.")
    private String documentName;

    @NotNull(message = "Пользователь не был указан.")
    private Long userDtoResponseId;
}
