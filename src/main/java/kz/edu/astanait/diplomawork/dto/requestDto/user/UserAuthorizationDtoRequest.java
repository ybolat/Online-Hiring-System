package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class UserAuthorizationDtoRequest {
    @NotNull(message = "The email cannot be empty.")
    private String email;

    @NotNull(message = "The password cannot be empty.")
    private String password;
}
