package kz.edu.astanait.diplomawork.mapper.user;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserProfessionalInfoDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicDegreeMapper;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicTitleMapper;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class UserProfessionalInfoMapper {

    public static UserProfessionalInfoDtoResponse userProfessionalInfoToDto (UserProfessionalInfo userProfessionalInfo){
        UserProfessionalInfoDtoResponse userProfessionalInfoDtoResponse = new UserProfessionalInfoDtoResponse();
        userProfessionalInfoDtoResponse.setId(userProfessionalInfo.getId());
        if(Objects.nonNull(userProfessionalInfo.getAcademicDegree())) userProfessionalInfoDtoResponse.setAcademicDegree(AcademicDegreeMapper.academicDegreeToDto(userProfessionalInfo.getAcademicDegree()));
        if(Objects.nonNull(userProfessionalInfo.getAcademicTitle())) userProfessionalInfoDtoResponse.setAcademicTitle(AcademicTitleMapper.academicTitleToDto(userProfessionalInfo.getAcademicTitle()));
        if(Strings.isNotBlank(userProfessionalInfo.getScopus())) userProfessionalInfoDtoResponse.setScopus(userProfessionalInfo.getScopus());
        if(Strings.isNotBlank(userProfessionalInfo.getResearch())) userProfessionalInfoDtoResponse.setResearch(userProfessionalInfo.getResearch());
        if(Strings.isNotBlank(userProfessionalInfo.getGoogleScholar())) userProfessionalInfoDtoResponse.setGoogleScholar(userProfessionalInfo.getGoogleScholar());
        if(Strings.isNotBlank(userProfessionalInfo.getOrcid())) userProfessionalInfoDtoResponse.setOrcid(userProfessionalInfo.getOrcid());
        if(Strings.isNotBlank(userProfessionalInfo.getExperience())) userProfessionalInfoDtoResponse.setExperience(userProfessionalInfo.getExperience());
        if(Strings.isNotBlank(userProfessionalInfo.getScientificInterests())) userProfessionalInfoDtoResponse.setScientificInterests(userProfessionalInfo.getScientificInterests());
        if(Strings.isNotBlank(userProfessionalInfo.getEducation())) userProfessionalInfoDtoResponse.setEducation(userProfessionalInfo.getEducation());
        return userProfessionalInfoDtoResponse;
    }
}
