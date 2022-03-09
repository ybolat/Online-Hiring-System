package kz.edu.astanait.diplomawork.dto.responseDto.catalog;

import lombok.Data;

import javax.persistence.Column;

@Data
public class PositionDtoResponse {

    private Long id;

    private String positionName;
}
