package kz.edu.astanait.diplomawork.mapper;

import kz.edu.astanait.diplomawork.dto.responseDto.UserDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicDegreeMapper;
import kz.edu.astanait.diplomawork.mapper.security.RoleMapper;
import kz.edu.astanait.diplomawork.model.User;
import org.apache.logging.log4j.util.Strings;
import java.util.Objects;

public class UserMapper {

    public static UserDtoResponse userToDto(User user) {
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setId(user.getId());
        if(Objects.nonNull(user.getRole())) userDtoResponse.setRole(RoleMapper.roleToDto(user.getRole()));
        if(Strings.isNotBlank(user.getEmail())) userDtoResponse.setEmail(user.getEmail());
        if(Strings.isNotBlank(user.getName())) userDtoResponse.setName(user.getName());
        if(Strings.isNotBlank(user.getLastname())) userDtoResponse.setLastname(user.getLastname());
        if(Strings.isNotBlank(user.getPatronymic())) userDtoResponse.setPatronymic(user.getPatronymic());
        if(Strings.isNotBlank(user.getPosition())) userDtoResponse.setPosition(user.getPosition());
        if(Strings.isNotBlank(user.getPhone())) userDtoResponse.setPhone(user.getPhone());
        if(Objects.nonNull(user.getCreatedDate())) userDtoResponse.setCreatedDate(user.getCreatedDate());
        if(Objects.nonNull(user.getAcademicDegree())) userDtoResponse.setAcademicDegree(AcademicDegreeMapper.academicDegreeToDto(user.getAcademicDegree()));
        if(Strings.isNotBlank(user.getAcademicTitle())) userDtoResponse.setAcademicTitle(user.getAcademicTitle());
        if(Strings.isNotBlank(user.getScopus())) userDtoResponse.setScopus(user.getScopus());
        if(Strings.isNotBlank(user.getResearch())) userDtoResponse.setResearch(user.getResearch());
        if(Strings.isNotBlank(user.getGoogleScholar())) userDtoResponse.setGoogleScholar(user.getGoogleScholar());
        if(Strings.isNotBlank(user.getOrcid())) userDtoResponse.setOrcid(user.getOrcid());
        if(Strings.isNotBlank(user.getExperience())) userDtoResponse.setExperience(user.getExperience());
        if(Strings.isNotBlank(user.getScientificInterests())) userDtoResponse.setScientificInterests(user.getScientificInterests());
        if(Strings.isNotBlank(user.getEducation())) userDtoResponse.setEducation(user.getEducation());
        return userDtoResponse;
    }
}
