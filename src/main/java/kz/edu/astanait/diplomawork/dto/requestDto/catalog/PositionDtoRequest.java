package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PositionDtoRequest {

    @NotNull(message = "Название позиции не был указан.")
    private String positionName;
}
