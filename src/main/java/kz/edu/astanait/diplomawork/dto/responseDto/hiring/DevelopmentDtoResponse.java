package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import lombok.Data;

@Data
public class DevelopmentDtoResponse {

    private Long id;

    private UserDtoResponse user;

    private String name;

    private String description;
}
