package kz.edu.astanait.diplomawork.mapper.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.StatusDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.Status;
import org.apache.logging.log4j.util.Strings;

public class StatusMapper {

    public static StatusDtoResponse statusToDto(Status status) {
        StatusDtoResponse statusDtoResponse = new StatusDtoResponse();
        statusDtoResponse.setId(status.getId());
        if(Strings.isNotBlank(status.getStatusName())) statusDtoResponse.setStatusName(status.getStatusName());
        return statusDtoResponse;
    }
}
