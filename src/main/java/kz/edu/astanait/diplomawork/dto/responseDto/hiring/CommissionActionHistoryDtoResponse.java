package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.user.CommissionDtoResponse;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommissionActionHistoryDtoResponse {

    private Long id;

    private CommissionDtoResponse commission;

    private RequestDtoResponse request;

    private Boolean isVote;
}
