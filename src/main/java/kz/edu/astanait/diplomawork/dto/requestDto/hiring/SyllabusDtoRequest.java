package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SyllabusDtoRequest {

    @NotNull(message = "The subject is not specified.")
    private Long subjectId;
}
