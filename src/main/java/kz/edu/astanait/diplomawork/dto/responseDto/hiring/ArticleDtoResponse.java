package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.ArticleTypeDtoResponse;
import lombok.Data;

@Data
public class ArticleDtoResponse {

    private Long id;

    private String apa;

    private String doi;

    private UserDtoResponse user;

    private ArticleTypeDtoResponse articleType;

    private String link;
}
