package kz.edu.astanait.diplomawork.mapper.user;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicDegreeMapper;
import kz.edu.astanait.diplomawork.mapper.security.RoleMapper;
import kz.edu.astanait.diplomawork.model.user.User;
import org.apache.logging.log4j.util.Strings;
import java.util.Objects;

public class UserMapper {

    public static UserDtoResponse userToDto(User user) {
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setId(user.getId());
        if(Strings.isNotBlank(user.getEmail())) userDtoResponse.setEmail(user.getEmail());
        if(Objects.nonNull(user.getRole())) userDtoResponse.setRole(RoleMapper.roleToDto(user.getRole()));
        if(Strings.isNotBlank(user.getName())) userDtoResponse.setName(user.getName());
        if(Strings.isNotBlank(user.getLastname())) userDtoResponse.setLastname(user.getLastname());
        if(Strings.isNotBlank(user.getPatronymic())) userDtoResponse.setPatronymic(user.getPatronymic());
        if(Strings.isNotBlank(user.getPhone())) userDtoResponse.setPhone(user.getPhone());
        userDtoResponse.setActive(user.isActive());
        userDtoResponse.setLocked(user.isLocked());
        if(Objects.nonNull(user.getCreatedDate())) userDtoResponse.setCreatedDate(user.getCreatedDate());
        return userDtoResponse;
    }
}
