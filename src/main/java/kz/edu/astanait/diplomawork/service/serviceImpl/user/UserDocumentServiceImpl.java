package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserDocumentDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.user.UserDocument;
import kz.edu.astanait.diplomawork.repository.user.UserDocumentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DocumentsService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserDocumentService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserDocumentServiceImpl implements UserDocumentService {

    private final UserDocumentRepository userDocumentRepository;

    private final UserService userService;
    private final DocumentsService documentsService;

    @Autowired
    public UserDocumentServiceImpl(UserDocumentRepository userDocumentRepository, UserService userService, DocumentsService documentsService) {
        this.userDocumentRepository = userDocumentRepository;
        this.userService = userService;
        this.documentsService = documentsService;
    }

    @Override
    public Optional<UserDocument> getById(Long id) {
        return this.userDocumentRepository.findById(id);
    }

    @Override
    public UserDocument getByIdThrowException(Long id) {
        return this.getById(id).
                orElseThrow(() -> new CustomNotFoundException(String.format(ExceptionDescription.CustomNotFoundException, "user document", "id", id)));
    }

    @Override
    public Optional<UserDocument> getByUserId(Long id) {
        return this.userDocumentRepository.findByUserId(id);
    }

    @Override
    public UserDocument getByUserIdThrowException(Long id) {
        return this.getByUserId(id).orElseThrow
                (() -> new CustomNotFoundException(String.format(ExceptionDescription.CustomNotFoundException, "user document", "user id", id)));
    }

    @Override
    public void create(UserDocumentDtoRequest userDocumentDtoRequest, Principal principal) {
        UserDocument userDocument = new UserDocument();

        userDocument.setUser(this.userService.getByEmailThrowException(principal.getName()));
        userDocument.setCv(this.documentsService.create("CV", userDocumentDtoRequest.getCv()));
        userDocument.setPassport(this.documentsService.create("Passport", userDocumentDtoRequest.getPassport()));
        userDocument.setPhoto(this.documentsService.create("Photo", userDocumentDtoRequest.getPhoto()));

        try {
            this.userDocumentRepository.save(userDocument);
        }catch (Exception e) {
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "uesr documents"));
        }
    }

    @Override
    public void update(UserDocumentDtoRequest userDocumentDtoRequest, Long id) {
        UserDocument userDocument = this.getByIdThrowException(id);

        if (Objects.nonNull(userDocumentDtoRequest.getCv())) userDocument.setCv(this.documentsService.update("CV", userDocumentDtoRequest.getCv(), userDocument.getCv().getId()));
        if (Objects.nonNull(userDocumentDtoRequest.getPassport())) userDocument.setPassport(this.documentsService.update("Passport", userDocumentDtoRequest.getPassport(), userDocument.getPassport().getId()));
        if (Objects.nonNull(userDocumentDtoRequest.getPhoto())) userDocument.setPhoto(this.documentsService.update("Photo", userDocumentDtoRequest.getPhoto(), userDocument.getPhoto().getId()));

        try {
            this.userDocumentRepository.save(userDocument);
        }catch (Exception e) {
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "user documents"));
        }
    }

    @Override
    public void delete(Long id) {
        UserDocument userDocument = this.getByIdThrowException(id);

        this.documentsService.delete(userDocument.getPhoto().getId());
        this.documentsService.delete(userDocument.getCv().getId());
        this.documentsService.delete(userDocument.getPassport().getId());

        try {
            this.userDocumentRepository.delete(userDocument);
        }catch (Exception e) {
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "user documents"));
        }
    }
}
