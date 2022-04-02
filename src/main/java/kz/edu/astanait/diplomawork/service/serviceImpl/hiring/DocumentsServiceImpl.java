package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.DocumentsDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Documents;
import kz.edu.astanait.diplomawork.repository.hiring.DocumentsRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DocumentsService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class DocumentsServiceImpl implements DocumentsService {

    private final DocumentsRepository documentsRepository;

    private final UserService userService;

    @Autowired
    public DocumentsServiceImpl(DocumentsRepository documentsRepository, UserService userService) {
        this.documentsRepository = documentsRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Documents> getById(Long id) {
        return this.documentsRepository.findById(id);
    }

    @Override
    public Documents getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> new CustomNotFoundException(
                String.format(ExceptionDescription.CustomNotFoundException, "Documents", "id", id)));
    }

    @Override
    public void create(DocumentsDtoRequest documentsDtoRequest, Principal principal) throws IOException {
        Documents documents = new Documents();

        documents.setDocument(documentsDtoRequest.getDocument().getBytes());
        documents.setContentType(documentsDtoRequest.getDocument().getContentType());
        documents.setDocumentName(documentsDtoRequest.getDocumentName());
        documents.setUser(this.userService.getByEmailThrowException(principal.getName()));

        try {
            this.documentsRepository.save(documents);
        }catch (Exception e){
         log.error(e);
         throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "documents"));
        }
    }

    @Override
    public void update(DocumentsDtoRequest documentsDtoRequest, Long id) throws IOException {
        Documents documents = this.getByIdThrowException(id);

        if(Objects.nonNull(documentsDtoRequest.getDocument())) {
            documents.setDocument(documentsDtoRequest.getDocument().getBytes());
            documents.setContentType(documentsDtoRequest.getDocument().getContentType());
        }
        if(Strings.isNotBlank(documentsDtoRequest.getDocumentName())) documents.setDocumentName(documentsDtoRequest.getDocumentName());

        try {
            this.documentsRepository.save(documents);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "documents"));
        }
    }

    @Override
    public void delete(Long id) {
        Documents documents = this.getByIdThrowException(id);

        try {
            this.documentsRepository.delete(documents);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "documents"));
        }
    }
}
