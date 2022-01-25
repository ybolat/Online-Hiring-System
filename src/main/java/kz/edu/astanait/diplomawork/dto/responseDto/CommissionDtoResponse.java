package kz.edu.astanait.diplomawork.dto.responseDto;

import kz.edu.astanait.diplomawork.dto.responseDto.security.RoleDtoResponse;
import lombok.Data;

@Data
public class CommissionDtoResponse {

    private Long id;

    private RoleDtoResponse role;

    private String email;
}
