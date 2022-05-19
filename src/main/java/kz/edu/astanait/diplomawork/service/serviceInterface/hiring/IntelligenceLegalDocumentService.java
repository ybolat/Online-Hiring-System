package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.IntelligenceLegalDocumentDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IntelligenceLegalDocumentService {

    List<IntelligenceLegalDocument> getAllByUserProfessionalInfoId(Long id);

    Optional<IntelligenceLegalDocument> getById(Long id);

    IntelligenceLegalDocument getByIdThrowException(Long id);

    void create(String fileName, MultipartFile file, Principal principal);

    void update(Long id, String fileName, MultipartFile file, Principal principal);

    void delete(Long id, Principal principal);

    void createAll(HashMap<String, MultipartFile> file, Principal principal);
}
