package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;

import java.util.List;
import java.util.Optional;

public interface IntelligenceLegalDocumentService {
    List<IntelligenceLegalDocument> getAllByUserId(Long id);

    Optional<IntelligenceLegalDocument> getById(Long id);

    IntelligenceLegalDocument getByIdThrowException(Long id);
}
