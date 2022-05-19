package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.PublicationsDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Publications;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PublicationsService {

    List<Publications> getAllByUserProfessionalInfoId(Long id);

    Optional<Publications> getById(Long id);

    Publications getByIdThrowException(Long id);

    void create(PublicationsDtoRequest publicationsDtoRequest, Principal principal);

    void update(PublicationsDtoRequest publicationsDtoRequest, Long id);

    void delete(Long id);

    void createAll(List<PublicationsDtoRequest> publicationsDtoRequestList, Principal principal);
}
