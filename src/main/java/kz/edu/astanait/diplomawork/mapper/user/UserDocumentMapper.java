package kz.edu.astanait.diplomawork.mapper.user;

import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDocumentDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.DocumentMapper;
import kz.edu.astanait.diplomawork.model.user.UserDocument;

import java.util.Objects;

public class UserDocumentMapper {

    public static UserDocumentDtoResponse userDocumentToDto(UserDocument userDocument){
        UserDocumentDtoResponse userDocumentDtoResponse = new UserDocumentDtoResponse();
        userDocumentDtoResponse.setId(userDocument.getId());
        if (Objects.nonNull(userDocument.getUser())) userDocumentDtoResponse.setUser(UserMapper.userToDto(userDocument.getUser()));
        if (Objects.nonNull(userDocument.getCv())) userDocumentDtoResponse.setCv(DocumentMapper.documentsToDto(userDocument.getCv()));
        if (Objects.nonNull(userDocument.getPassport())) userDocumentDtoResponse.setPassport(DocumentMapper.documentsToDto(userDocument.getPassport()));
        if (Objects.nonNull(userDocument.getPhoto())) userDocumentDtoResponse.setPhoto(DocumentMapper.documentsToDto(userDocument.getPhoto()));
        return userDocumentDtoResponse;
    }
}
