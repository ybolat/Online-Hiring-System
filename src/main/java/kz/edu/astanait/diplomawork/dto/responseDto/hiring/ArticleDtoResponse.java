package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.UserDtoResponse;
import kz.edu.astanait.diplomawork.model.User;
import lombok.Data;

@Data
public class ArticleDtoResponse {

    private Long id;

    private String apa;

    private String doi;

    private UserDtoResponse user;

    private String articleType;

    private String link;
}
