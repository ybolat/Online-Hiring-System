package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.DevelopmentDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.DevelopmentTypeMapper;
import kz.edu.astanait.diplomawork.mapper.user.UserProfessionalInfoMapper;
import kz.edu.astanait.diplomawork.model.hiring.Development;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class DevelopmentMapper {

    public static DevelopmentDtoResponse developmentToDto(Development development) {
        DevelopmentDtoResponse developmentDtoResponse = new DevelopmentDtoResponse();
        developmentDtoResponse.setId(development.getId());
//        if(Objects.nonNull(development.getUserProfessionalInfo())) developmentDtoResponse.setUserProfessionalInfoDtoResponse(UserProfessionalInfoMapper.userProfessionalInfoToDto(development.getUserProfessionalInfo()));
        if(Strings.isNotBlank(development.getName())) developmentDtoResponse.setName(development.getName());
        if(Strings.isNotBlank(development.getDescription())) developmentDtoResponse.setDescription(development.getDescription());
        if(Objects.nonNull(development.getDevelopmentType())) developmentDtoResponse.setDevelopmentTypeDtoResponse(DevelopmentTypeMapper.developmentTypeToDto(development.getDevelopmentType()));
        return developmentDtoResponse;
    }
}
