package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDtoRequest {

    @NotNull(message = "The email address cannot be empty")
    private String email;

    @NotNull(message = "The password cannot be empty.")
    @Size(min = 8, message = "The password must be at least 8 characters long.")
    private String password;
    
    @NotNull(message = "The name cannot be empty.")
    private String name;

    @NotNull(message = "The lastname cannot be empty.")
    private String lastname;

    private String patronymic;

    @NotNull(message = "The phone number cannot be empty.")
    private String phone;

}
