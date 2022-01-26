package kz.edu.astanait.diplomawork.mapper.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.ArticleTypeDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.ArticleType;
import org.apache.logging.log4j.util.Strings;

public class ArticleTypeMapper {

    public static ArticleTypeDtoResponse articleTypeToDto(ArticleType articleType) {

        ArticleTypeDtoResponse articleTypeDtoResponse = new ArticleTypeDtoResponse();
        articleTypeDtoResponse.setId(articleType.getId());
        if(Strings.isNotBlank(articleType.getTitle())) articleTypeDtoResponse.setTitle(articleType.getTitle());
        return articleTypeDtoResponse;
    }
}
