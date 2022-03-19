package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommissionActionHistoryDtoResponse {

    private Long id;

    private Long commission;

    private Long request;

    private Boolean isVote;
}
