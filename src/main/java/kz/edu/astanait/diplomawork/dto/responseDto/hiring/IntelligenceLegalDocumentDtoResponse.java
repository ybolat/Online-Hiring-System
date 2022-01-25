package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.UserDtoResponse;
import lombok.Data;

@Data
public class IntelligenceLegalDocumentDtoResponse {

    private Long id;

    private UserDtoResponse user;

    private String document;
}
