package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.SubjectDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.user.UserProfessionalInfoDtoResponse;
import lombok.Data;

@Data
public class SyllabusDtoResponse {

    private Long id;

    private UserProfessionalInfoDtoResponse userProfessionalInfoDtoResponse;

    private SubjectDtoResponse subject;
}
