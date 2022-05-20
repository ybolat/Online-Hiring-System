package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserDocumentDtoRequest implements Serializable {

    @NotNull(message = "Резюме не может быть пустым.")
    private MultipartFile cv;

    @NotNull(message = "Пасспорт не может быть пустым.")
    private MultipartFile passport;

    @NotNull(message = "Фото не может быть пустым.")
    private MultipartFile photo;
}
