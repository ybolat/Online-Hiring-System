package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;
import kz.edu.astanait.diplomawork.repository.hiring.IntelligenceLegalDocumentRepository;

import java.util.List;

public interface IntelligenceLegalDocumentService {
    List<IntelligenceLegalDocument> getAllByUserId(Long id);
}
