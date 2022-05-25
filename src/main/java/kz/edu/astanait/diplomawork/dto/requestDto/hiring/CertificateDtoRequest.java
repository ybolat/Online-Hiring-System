package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CertificateDtoRequest {

    @NotNull(message = "The document was not specified.")
    private Long documentsId;
}
