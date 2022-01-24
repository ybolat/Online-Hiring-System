package kz.edu.astanait.diplomawork.dto.responseDto.security;

import lombok.Data;

import javax.persistence.*;

@Data
public class RoleDtoResponse {

    private Long id;

    private String roleName;
}
