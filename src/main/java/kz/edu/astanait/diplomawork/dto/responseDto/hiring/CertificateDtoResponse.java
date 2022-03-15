package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.user.UserProfessionalInfoDtoResponse;
import lombok.Data;

@Data
public class CertificateDtoResponse {

    private Long id;

    private UserProfessionalInfoDtoResponse userProfessionalInfoDtoResponse;

    private String certificate;
}
