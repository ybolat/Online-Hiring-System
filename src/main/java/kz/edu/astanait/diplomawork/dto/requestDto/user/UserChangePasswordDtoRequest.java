package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserChangePasswordDtoRequest {

    @NotNull(message = "The password cannot be empty.")
    @Size(min = 8, message = "The password must be at least 8 characters long.")
    private String password;
}
