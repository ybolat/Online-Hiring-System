package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserProfessionalInfoDtoResponse;
import lombok.Data;

@Data
public class IntelligenceLegalDocumentDtoResponse {

    private Long id;

//    private UserProfessionalInfoDtoResponse userProfessionalInfoDtoResponse;

    private DocumentsDtoResponse document;
}
