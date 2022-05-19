package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserChangePasswordDtoRequest {

    @NotNull(message = "Пароль не может быть пустым.")
    @Size(min = 8, message = "Длина пароля должна быть не менее 8 символов.")
    private String password;
}
