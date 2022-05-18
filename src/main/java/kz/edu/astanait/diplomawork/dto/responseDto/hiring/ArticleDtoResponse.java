package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.ArticleTypeDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.user.UserProfessionalInfoDtoResponse;
import lombok.Data;

@Data
public class ArticleDtoResponse {

    private Long id;

    private String apa;

    private String doi;

    private UserProfessionalInfoDtoResponse userProfessionalInfoDtoResponse;

    private ArticleTypeDtoResponse articleType;

    private String link;
}
