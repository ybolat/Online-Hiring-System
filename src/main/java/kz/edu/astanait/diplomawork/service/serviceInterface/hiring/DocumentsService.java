package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface DocumentsService {

    Optional<Document> getById(Long id);

    Document getByIdThrowException(Long id);

    Document create(String fileName, MultipartFile file);

    Document update(String fileName, MultipartFile file, Long id);

    void delete(Long id);
}
