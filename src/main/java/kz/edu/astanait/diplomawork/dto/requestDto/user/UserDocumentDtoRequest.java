package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserDocumentDtoRequest implements Serializable {

    @NotNull(message = "The cv cannot be empty.")
    private MultipartFile cv;

    @NotNull(message = "The passport cannot be empty.")
    private MultipartFile passport;

    @NotNull(message = "The photo cannot be empty.")
    private MultipartFile photo;
}
