package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;
import kz.edu.astanait.diplomawork.repository.hiring.IntelligenceLegalDocumentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.IntelligenceLegalDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<IntelligenceLegalDocument> getById(Long id) {
        return this.intelligenceLegalDocumentRepository.findById(id);
    }

    @Override
    public IntelligenceLegalDocument getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Intelligence Legal Document", "id", id)));
    }
}
