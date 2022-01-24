package kz.edu.astanait.diplomawork.dto.responseDto;

import kz.edu.astanait.diplomawork.dto.responseDto.security.RoleDtoResponse;
import kz.edu.astanait.diplomawork.model.security.Role;
import lombok.Data;

import javax.persistence.*;

@Data
public class CommissionDtoResponse {

    private Long id;

    private RoleDtoResponse role;

    private String email;
}
