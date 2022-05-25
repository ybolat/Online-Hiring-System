package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class DocumentsDtoRequest {

    @NotNull(message = "The document was not specified.")
    private MultipartFile document;

    @NotNull(message = "The document name was not specified.")
    private String documentName;
}
