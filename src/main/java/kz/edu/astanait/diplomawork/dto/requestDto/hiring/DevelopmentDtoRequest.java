package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DevelopmentDtoRequest {

    @NotNull(message = "Название не было указано.")
    private String name;

    @NotNull(message = "Описание не было указано.")
    private String description;

    @NotNull(message = "Тип разработки не был указан.")
    private Long developmentTypeId;
}
