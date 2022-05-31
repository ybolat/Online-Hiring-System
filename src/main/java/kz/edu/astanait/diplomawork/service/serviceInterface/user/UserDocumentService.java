package kz.edu.astanait.diplomawork.service.serviceInterface.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserDocumentDtoRequest;
import kz.edu.astanait.diplomawork.model.user.UserDocument;

import java.security.Principal;
import java.util.Optional;

public interface UserDocumentService {

    Optional<UserDocument> getById(Long id);

    Optional<UserDocument> getByUserEmail(Principal principal);

    UserDocument getByIdThrowException(Long id);

    Optional<UserDocument> getByUserId(Long id);

    UserDocument getByUserIdThrowException(Long id);

    void create(UserDocumentDtoRequest userDocumentDtoRequest, Principal principal);

    void update(UserDocumentDtoRequest userDocumentDtoRequest, Long id);

    void delete(Long id);
}
