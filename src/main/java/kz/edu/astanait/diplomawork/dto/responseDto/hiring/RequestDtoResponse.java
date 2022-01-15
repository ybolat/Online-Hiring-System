package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.model.User;
import kz.edu.astanait.diplomawork.model.catalog.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDtoResponse {

    private Long id;

    private User user;

    private Status status;

    private String background;

    private String experience;

    private String additional;

    private LocalDateTime createdDate;

}
