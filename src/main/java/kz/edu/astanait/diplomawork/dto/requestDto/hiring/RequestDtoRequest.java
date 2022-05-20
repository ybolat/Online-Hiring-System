package kz.edu.astanait.diplomawork.dto.requestDto.hiring;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RequestDtoRequest {

    @NotNull(message = "Время создания не была указана.")
    private LocalDateTime createdDate;

    private String additional;
}
