package kz.edu.astanait.diplomawork.mapper.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.DevelopmentTypeDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.DevelopmentType;
import org.apache.logging.log4j.util.Strings;

public class DevelopmentTypeMapper {

    public static DevelopmentTypeDtoResponse developmentTypeToDto(DevelopmentType developmentType) {

        DevelopmentTypeDtoResponse developmentTypeDtoResponse = new DevelopmentTypeDtoResponse();
        developmentTypeDtoResponse.setId(developmentType.getId());
        if(Strings.isNotBlank(developmentType.getTitle())) developmentTypeDtoResponse.setTitle(developmentType.getTitle());
        return developmentTypeDtoResponse;
    }
}
