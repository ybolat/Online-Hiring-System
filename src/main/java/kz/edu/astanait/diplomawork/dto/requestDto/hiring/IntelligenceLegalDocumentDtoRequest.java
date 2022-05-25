package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IntelligenceLegalDocumentDtoRequest {

    @NotNull(message = "The document was not specified.")
    private Long documentId;
}
