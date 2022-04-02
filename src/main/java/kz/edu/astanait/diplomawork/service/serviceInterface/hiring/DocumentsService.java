package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.DocumentsDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Documents;

import java.util.Optional;

public interface DocumentsService {

    Optional<Documents> getById(Long id);

    Documents getByIdThrowException(Long id);

    void create(DocumentsDtoRequest documentsDtoRequest);

    void update(DocumentsDtoRequest documentsDtoRequest, Long id);

    void delete(Long id);
}
