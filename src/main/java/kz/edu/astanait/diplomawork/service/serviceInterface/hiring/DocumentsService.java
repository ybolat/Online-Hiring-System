package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Documents;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface DocumentsService {

    Optional<Documents> getById(Long id);

    Documents getByIdThrowException(Long id);

    Documents create(String fileName, MultipartFile file);

    Documents update(String fileName, MultipartFile file, Long id);

    void delete(Long id);
}
