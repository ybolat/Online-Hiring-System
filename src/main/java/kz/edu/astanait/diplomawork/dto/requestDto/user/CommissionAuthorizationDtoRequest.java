package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommissionAuthorizationDtoRequest {

    @NotNull(message = "Почта не может быть пустым.")
    private String email;
}
