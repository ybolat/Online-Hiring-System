package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.PublicationsDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.UserProfessionalInfoMapper;
import kz.edu.astanait.diplomawork.model.hiring.Publications;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class PublicationsMapper {

    public static PublicationsDtoResponse publicationsToDto(Publications publications) {

        PublicationsDtoResponse publicationsDtoResponse = new PublicationsDtoResponse();
        publicationsDtoResponse.setId(publications.getId());
        if(Strings.isNotBlank(publications.getName())) publicationsDtoResponse.setName(publications.getName());
        if(Strings.isNotBlank(publications.getLink())) publicationsDtoResponse.setLink(publications.getLink());
        if(Objects.nonNull(publications.getPublishedDate())) publicationsDtoResponse.setPublishedDate(publications.getPublishedDate());
        if(Objects.nonNull(publications.getUserProfessionalInfo())) publicationsDtoResponse.setUserProfessionalInfo(UserProfessionalInfoMapper.userProfessionalInfoToDto(publications.getUserProfessionalInfo()));
        return publicationsDtoResponse;
    }
}
