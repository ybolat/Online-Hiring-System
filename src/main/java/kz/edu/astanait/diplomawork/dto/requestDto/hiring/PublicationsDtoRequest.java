package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class PublicationsDtoRequest {

    @NotNull(message = "Название не было указано.")
    private String name;

    @NotNull(message = "Ссылка не была указана.")
    private String link;

    @NotNull(message = "Дата публикации не была указана.")
    private LocalDateTime publishedDate;
}
