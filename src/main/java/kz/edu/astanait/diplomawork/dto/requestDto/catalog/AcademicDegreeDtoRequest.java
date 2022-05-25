package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AcademicDegreeDtoRequest {

    @NotNull(message = "The title was not specified.")
    private String title;
}
