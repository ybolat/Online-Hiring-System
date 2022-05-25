package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class ArticleDtoRequest {

    @NotNull(message = "The name was not specified.")
    private String title;

    private String apa;

    @NotNull(message = "DOI не был указан.")
    private String doi;

    @NotNull(message = "The article type was not specified.")
    private Long articleTypeId;

    @NotNull(message = "The authors were not identified.")
    private String authors;

    @NotNull(message = "The publication was not specified.")
    private String source;
}
