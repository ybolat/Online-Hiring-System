package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.SubjectDtoResponse;
import lombok.Data;

@Data
public class SyllabusDtoResponse {

    private Long id;

    private UserDtoResponse user;

    private SubjectDtoResponse subject;
}
