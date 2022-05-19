package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.IntelligenceLegalDocumentDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface IntelligenceLegalDocumentService {

    List<IntelligenceLegalDocument> getAllByUserProfessionalInfoId(Long id);

    Optional<IntelligenceLegalDocument> getById(Long id);

    IntelligenceLegalDocument getByIdThrowException(Long id);

    void create(IntelligenceLegalDocumentDtoRequest intelligenceLegalDocumentDtoRequest, Principal principal);

    void update(IntelligenceLegalDocumentDtoRequest intelligenceLegalDocumentDtoRequest, Long id);

    void delete(Long id);

    void createAll(List<IntelligenceLegalDocumentDtoRequest> intelligenceLegalDocumentDtoRequestList, Principal principal);
}
