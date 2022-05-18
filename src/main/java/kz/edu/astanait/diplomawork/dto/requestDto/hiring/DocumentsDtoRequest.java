package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class DocumentsDtoRequest {

    @NotNull(message = "Документ не был отправлен.")
    private MultipartFile document;

    @NotNull(message = "Тип контента не был указан.")
    private String contentType;

    @NotNull(message = "Название документа не было указано.")
    private String documentName;
}
