package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.RequestDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.UserMapper;
import kz.edu.astanait.diplomawork.mapper.catalog.StatusMapper;
import kz.edu.astanait.diplomawork.model.hiring.Request;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class RequestMapper {

    public static RequestDtoResponse requestToDto(Request request) {
        RequestDtoResponse requestDtoResponse = new RequestDtoResponse();
        requestDtoResponse.setId(request.getId());
        if (Objects.nonNull(request.getUser())) requestDtoResponse.setUser(UserMapper.userToDto(request.getUser()));
        if (Objects.nonNull(request.getStatus())) requestDtoResponse.setStatus(StatusMapper.statusToDto(request.getStatus()));
        if (Strings.isNotBlank(request.getBackground())) requestDtoResponse.setBackground(request.getBackground());
        if (Strings.isNotBlank(request.getAdditional())) requestDtoResponse.setAdditional(request.getAdditional());
        if (Objects.nonNull(request.getCreatedDate())) requestDtoResponse.setCreatedDate(request.getCreatedDate());
        return requestDtoResponse;
    }
}
