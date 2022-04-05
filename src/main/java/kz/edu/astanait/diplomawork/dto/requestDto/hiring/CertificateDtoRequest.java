package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CertificateDtoRequest {

    @NotNull(message = "Документ не был указан.")
    private Long documentsId;
}
