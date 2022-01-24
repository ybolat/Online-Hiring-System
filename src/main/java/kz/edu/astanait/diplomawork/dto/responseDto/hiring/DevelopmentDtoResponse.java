package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.UserDtoResponse;
import kz.edu.astanait.diplomawork.model.User;
import lombok.Data;

@Data
public class DevelopmentDtoResponse {

    private Long id;

    private UserDtoResponse user;

    private String name;

    private String developmentDescription;
}
