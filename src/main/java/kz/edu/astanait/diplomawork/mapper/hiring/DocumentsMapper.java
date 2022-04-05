package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.DocumentsDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.UserMapper;
import kz.edu.astanait.diplomawork.model.hiring.Documents;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class DocumentsMapper {

    public static DocumentsDtoResponse documentsToDto(Documents documents) {
        DocumentsDtoResponse documentsDtoResponse = new DocumentsDtoResponse();
        documents.setId(documents.getId());
        if(Strings.isNotBlank(documents.getDocument())) documentsDtoResponse.setDocument(documents.getDocument());
        if(Strings.isNotBlank(documents.getDocumentName())) documentsDtoResponse.setDocumentName(documents.getDocumentName());
        return documentsDtoResponse;
    }
}
