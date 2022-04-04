package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class ArticleDtoRequest {

    @NotNull(message = "APA не был указан.")
    private String apa;

    @NotNull(message = "DOI не был указан.")
    private String doi;

    @NotNull(message = "Тип статьи не был указан.")
    private Long articleTypeId;

    @NotNull(message = "Ссылка не была указана.")
    private String link;
}
