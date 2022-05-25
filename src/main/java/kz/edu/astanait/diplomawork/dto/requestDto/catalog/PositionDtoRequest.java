package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PositionDtoRequest {

    @NotNull(message = "The position name was not specified.")
    private String positionName;
}
