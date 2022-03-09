package kz.edu.astanait.diplomawork.mapper.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.PositionDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.Position;
import org.apache.logging.log4j.util.Strings;

public class PositionMapper {

    public static PositionDtoResponse positionToDto(Position position) {

        PositionDtoResponse positionDtoResponse = new PositionDtoResponse();
        positionDtoResponse.setId(position.getId());
        if(Strings.isNotBlank(position.getPositionName())) positionDtoResponse.setPositionName(position.getPositionName());
        return positionDtoResponse;
    }
}
