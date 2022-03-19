package kz.edu.astanait.diplomawork.dto.responseDto.user;

import kz.edu.astanait.diplomawork.dto.responseDto.security.RoleDtoResponse;
import kz.edu.astanait.diplomawork.model.security.Role;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDtoResponse {

    private Long id;

    private String email;

    private RoleDtoResponse role;

    private String name;

    private String lastname;

    private String patronymic;

    private String phone;

    private LocalDateTime createdDate;

    private boolean isActive;

    private boolean isLocked;

    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}