package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.IntelligenceLegalDocumentDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.UserMapper;
import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class IntelligenceLegalDocumentMapper {

    public static IntelligenceLegalDocumentDtoResponse intelligenceLegalDocumentToDto(IntelligenceLegalDocument intelligenceLegalDocument) {

        IntelligenceLegalDocumentDtoResponse intelligenceLegalDocumentDtoResponse = new IntelligenceLegalDocumentDtoResponse();
        intelligenceLegalDocumentDtoResponse.setId(intelligenceLegalDocument.getId());
        if(Objects.nonNull(intelligenceLegalDocument.getUser())) intelligenceLegalDocumentDtoResponse.setUser(UserMapper.userToDto(intelligenceLegalDocument.getUser()));
        if(Strings.isNotBlank(intelligenceLegalDocument.getDocument())) intelligenceLegalDocumentDtoResponse.setDocument(intelligenceLegalDocument.getDocument());
        return intelligenceLegalDocumentDtoResponse;
    }
}
