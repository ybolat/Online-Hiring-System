package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import lombok.Data;

@Data
public class AssessmentDtoResponse {

    private Long id;

    private RequestDtoResponse request;

    private Boolean vote;
}
