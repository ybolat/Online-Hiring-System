package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserAuthorizationDtoRequest {
    @NotNull(message = "Адрес почты не может быть пустым.")
    private String email;

    @NotNull(message = "Пароль не может быть пустым.")
    @Min(value = 8)
    private String password;
}
