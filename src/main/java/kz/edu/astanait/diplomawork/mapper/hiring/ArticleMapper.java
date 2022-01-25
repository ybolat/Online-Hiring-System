package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.ArticleDtoResponse;
import kz.edu.astanait.diplomawork.mapper.UserMapper;
import kz.edu.astanait.diplomawork.model.hiring.Article;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class ArticleMapper {

    public static ArticleDtoResponse articleToDto(Article article) {
        ArticleDtoResponse articleDtoResponse = new ArticleDtoResponse();
        articleDtoResponse.setId(article.getId());
        if(Strings.isNotBlank(article.getApa())) articleDtoResponse.setApa(article.getApa());
        if(Strings.isNotBlank(article.getDoi())) articleDtoResponse.setDoi(article.getDoi());
        if(Objects.nonNull(article.getUser())) articleDtoResponse.setUser(UserMapper.userToDto(article.getUser()));
        if(Strings.isNotBlank(article.getArticleType())) articleDtoResponse.setArticleType(article.getArticleType());
        if(Strings.isNotBlank(article.getLink())) articleDtoResponse.setLink(article.getLink());
        return articleDtoResponse;
    }
}
