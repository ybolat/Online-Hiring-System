package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommissionDtoRequest {

    @NotNull(message = "Роль не может быть пустым.")
    private Long roleId;

    @NotNull(message = "Почта не может быть пустой.")
    private String email;
}
