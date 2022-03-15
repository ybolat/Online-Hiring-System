package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AssessmentDtoRequest {

    @NotNull(message = "Комиссия не была указана.")
    private Long commissionId;

    @NotNull(message = "Запрос не был указан.")
    private Long requestId;

    @NotNull(message = "Голос не был указан.")
    private Boolean vote;
}
