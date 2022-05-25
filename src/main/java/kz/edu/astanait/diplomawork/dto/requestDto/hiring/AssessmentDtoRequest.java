package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AssessmentDtoRequest {

    @NotNull(message = "The request was not specified.")
    private Long requestId;

    @NotNull(message = "The vote was not specified.")
    private Boolean vote;
}
