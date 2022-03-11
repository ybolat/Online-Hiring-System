package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticleTypeDtoRequest {

    @NotNull(message = "Заголовок не был указан.")
    private String title;
}
