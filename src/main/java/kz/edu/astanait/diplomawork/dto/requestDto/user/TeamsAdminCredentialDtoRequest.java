package kz.edu.astanait.diplomawork.dto.requestDto.user;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class TeamsAdminCredentialDtoRequest {

    @NotNull(message = "asd")
    private String directoryId;

    @NotNull(message = "asd")
    private String grantType;

    @NotNull(message = "asd")
    private String clientId;

    @NotNull(message = "asd")
    private String clientSecret;

    @NotNull(message = "asd")
    private Long commissionId;
}
