package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.StatusDtoResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDtoResponse {

    private Long id;

    private UserDtoResponse user;

    private String additional;

    private LocalDateTime createdDate;

}
