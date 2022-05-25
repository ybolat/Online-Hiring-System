package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class TeamsAdminCredentialDtoRequest {

    @NotNull(message = "The Directory cannot be empty.")
    private String directoryId;

    @NotNull(message = "The Grant type cannot be empty.")
    private String grantType;

    @NotNull(message = "The client id cannot be empty.")
    private String clientId;

    @NotNull(message = "The client secret cannot be empty.")
    private String clientSecret;
}
