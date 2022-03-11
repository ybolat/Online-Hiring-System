package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DevelopmentDtoRequest {

    @NotNull(message = "Пользователь не был указан.")
    private Long userId;

    @NotNull(message = "Название не было указано.")
    private String name;

    @NotNull(message = "Описание не было указано.")
    private String description;
}
