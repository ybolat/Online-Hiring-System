package kz.edu.astanait.diplomawork.mapper.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.PublicationsTypeDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.PublicationsType;
import kz.edu.astanait.diplomawork.model.hiring.Publications;
import kz.edu.astanait.diplomawork.repository.catalog.PublicationsTypeRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.parameters.P;

public class PublicationsTypeMapper {

    public static PublicationsTypeDtoResponse publicationsTypeToDto(PublicationsType publicationsType){

        PublicationsTypeDtoResponse publicationsTypeDtoResponse = new PublicationsTypeDtoResponse();
        publicationsTypeDtoResponse.setId(publicationsType.getId());
        if(Strings.isNotBlank(publicationsType.getName())) publicationsTypeDtoResponse.setName(publicationsType.getName());
        return publicationsTypeDtoResponse;
    }
}
