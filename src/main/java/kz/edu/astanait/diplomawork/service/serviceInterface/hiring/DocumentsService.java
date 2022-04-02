package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.DocumentsDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Documents;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

public interface DocumentsService {

    Optional<Documents> getById(Long id);

    Documents getByIdThrowException(Long id);

    void create(DocumentsDtoRequest documentsDtoRequest, Principal principal) throws IOException;

    void update(DocumentsDtoRequest documentsDtoRequest, Long id) throws IOException;

    void delete(Long id);
}
