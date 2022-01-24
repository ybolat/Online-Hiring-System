package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.UserDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.SubjectDtoResponse;
import kz.edu.astanait.diplomawork.model.User;
import kz.edu.astanait.diplomawork.model.catalog.Subject;
import lombok.Data;

@Data
public class SyllabusDtoResponse {

    private Long id;

    private UserDtoResponse user;

    private SubjectDtoResponse subject;
}
