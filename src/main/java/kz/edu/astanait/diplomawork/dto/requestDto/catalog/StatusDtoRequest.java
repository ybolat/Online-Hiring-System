package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatusDtoRequest {

    @NotNull(message = "The status name was not specified.")
    private String statusName;
}
