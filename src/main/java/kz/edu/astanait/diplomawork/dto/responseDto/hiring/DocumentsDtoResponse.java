package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import lombok.Data;


@Data
public class DocumentsDtoResponse {

    private Long id;

    private String document;

    private String documentName;
}
