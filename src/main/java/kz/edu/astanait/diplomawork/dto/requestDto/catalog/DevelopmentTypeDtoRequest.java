package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DevelopmentTypeDtoRequest {

    @NotNull(message = "Загаловок не был указан.")
    private String title;
}
