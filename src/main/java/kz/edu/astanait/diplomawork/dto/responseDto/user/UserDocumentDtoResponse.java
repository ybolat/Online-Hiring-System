package kz.edu.astanait.diplomawork.dto.responseDto.user;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.DocumentsDtoResponse;
import lombok.Data;

@Data
public class UserDocumentDtoResponse {

    private Long id;

    private DocumentsDtoResponse cv;

    private DocumentsDtoResponse passport;

    private DocumentsDtoResponse photo;

//    private UserDtoResponse user;
}
