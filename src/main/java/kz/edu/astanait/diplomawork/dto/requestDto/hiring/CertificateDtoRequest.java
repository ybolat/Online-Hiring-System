package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CertificateDtoRequest {

    @NotNull(message = "Пользователь не был указан.")
    private Long userId;

    @NotNull(message = "Сертификат не был указан.")
    private String certificate;
}
