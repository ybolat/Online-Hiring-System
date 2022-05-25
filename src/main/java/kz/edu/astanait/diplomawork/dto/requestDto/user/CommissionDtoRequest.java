package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommissionDtoRequest {

    @NotNull(message = "Role cannot be empty")
    private String role;

    @NotNull(message = "Email cannot be empty.")
    private String email;
}
