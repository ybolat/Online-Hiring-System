package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.DocumentsDtoResponse;
import kz.edu.astanait.diplomawork.model.hiring.Document;
import org.apache.logging.log4j.util.Strings;

public class DocumentMapper {

    public static DocumentsDtoResponse documentsToDto(Document documents) {
        DocumentsDtoResponse documentsDtoResponse = new DocumentsDtoResponse();
        documents.setId(documents.getId());
        if(Strings.isNotBlank(documents.getDocument())) documentsDtoResponse.setDocument(documents.getDocument());
        if(Strings.isNotBlank(documents.getContentType())) documentsDtoResponse.setContentType(documents.getContentType());
        if(Strings.isNotBlank(documents.getDocumentName())) documentsDtoResponse.setDocumentName(documents.getDocumentName());
        return documentsDtoResponse;
    }
}
