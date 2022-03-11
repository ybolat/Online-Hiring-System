package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatusDtoRequest {

    @NotNull(message = "Название статуса не был указан.")
    private String statusName;
}
