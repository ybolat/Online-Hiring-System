package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SyllabusDtoRequest {

    @NotNull(message = "Пользователь не был указан.")
    private Long userId;

    @NotNull(message = "Предмет не был указан.")
    private Long subjectId;
}
