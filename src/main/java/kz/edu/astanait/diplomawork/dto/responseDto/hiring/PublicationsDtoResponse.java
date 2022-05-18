package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.PublicationsTypeDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.user.UserProfessionalInfoDtoResponse;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PublicationsDtoResponse {

    private Long id;

    private String name;

    private String link;

    private LocalDateTime publishedDate;

    private PublicationsTypeDtoResponse publicationsTypeDtoResponse;

    private UserProfessionalInfoDtoResponse userProfessionalInfo;
}
