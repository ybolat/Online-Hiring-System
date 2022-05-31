package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.DevelopmentTypeDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.user.UserProfessionalInfoDtoResponse;
import lombok.Data;

@Data
public class DevelopmentDtoResponse {

    private Long id;

//    private UserProfessionalInfoDtoResponse userProfessionalInfoDtoResponse;

    private String name;

    private String description;

    private DevelopmentTypeDtoResponse developmentTypeDtoResponse;
}
