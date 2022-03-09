package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;
import kz.edu.astanait.diplomawork.repository.hiring.IntelligenceLegalDocumentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.IntelligenceLegalDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntelligenceLegalDocumentServiceImpl implements IntelligenceLegalDocumentService {

    private final IntelligenceLegalDocumentRepository intelligenceLegalDocumentRepository;

    @Autowired
    public IntelligenceLegalDocumentServiceImpl(IntelligenceLegalDocumentRepository intelligenceLegalDocumentRepository) {
        this.intelligenceLegalDocumentRepository = intelligenceLegalDocumentRepository;
    }

    @Override
    public List<IntelligenceLegalDocument> getAllByUserId(Long id) {
        return this.intelligenceLegalDocumentRepository.findAllByUserId(id);
    }
}
