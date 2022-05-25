package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DevelopmentDtoRequest {

    @NotNull(message = "The name was not specified.")
    private String name;

    @NotNull(message = "The description was not specified.")
    private String description;

    @NotNull(message = "The development type was not specified.")
    private Long developmentTypeId;
}
