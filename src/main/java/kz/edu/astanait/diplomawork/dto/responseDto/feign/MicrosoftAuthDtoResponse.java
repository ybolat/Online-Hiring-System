package kz.edu.astanait.diplomawork.dto.responseDto.feign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MicrosoftAuthDtoResponse {
    private String firstname;
    private String lastname;
    private String email;
}
