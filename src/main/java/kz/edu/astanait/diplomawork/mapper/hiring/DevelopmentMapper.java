package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.DevelopmentDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.UserMapper;
import kz.edu.astanait.diplomawork.model.hiring.Development;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class DevelopmentMapper {

    public static DevelopmentDtoResponse developmentToDto(Development development) {

        DevelopmentDtoResponse developmentDtoResponse = new DevelopmentDtoResponse();
        developmentDtoResponse.setId(development.getId());
        if(Objects.nonNull(development.getUser())) developmentDtoResponse.setUser(UserMapper.userToDto(development.getUser()));
        if(Strings.isNotBlank(development.getName())) developmentDtoResponse.setName(development.getName());
        if(Strings.isNotBlank(development.getDescription())) developmentDtoResponse.setDescription(development.getDescription());
        return developmentDtoResponse;
    }
}
