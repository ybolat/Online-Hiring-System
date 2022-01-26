package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.CommissionDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.UserDtoResponse;
import kz.edu.astanait.diplomawork.model.Commission;
import kz.edu.astanait.diplomawork.model.hiring.Request;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MeetingDtoResponse {

    private Long id;

    private String name;

    private String link;

    private LocalDateTime dateTime;

    private RequestDtoResponse request;

    private List<CommissionDtoResponse> commissionList;
}
