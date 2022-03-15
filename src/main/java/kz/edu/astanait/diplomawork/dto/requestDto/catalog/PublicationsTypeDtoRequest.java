package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PublicationsTypeDtoRequest {

    @NotNull(message = "Название не было указано.")
    private String name;
}
