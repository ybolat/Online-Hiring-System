package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Documents;
import kz.edu.astanait.diplomawork.repository.hiring.DocumentsRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DocumentsService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class DocumentsServiceImpl implements DocumentsService {

    private final DocumentsRepository documentsRepository;

    @Autowired
    public DocumentsServiceImpl(DocumentsRepository documentsRepository) {
        this.documentsRepository = documentsRepository;
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
    public Documents create(String fileName, MultipartFile file)   {
        Documents documents = new Documents();

        byte[] bytes;

        try {
            bytes = file.getInputStream().readAllBytes();
        } catch (IOException e) {
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "documents"));
        }

        byte[] encoded = Base64.encodeBase64(bytes);
        String encodedString = new String(encoded);

        documents.setDocument(encodedString);
        documents.setContentType(file.getContentType());
        documents.setDocumentName(fileName);

        try {
            return this.documentsRepository.save(documents);
        }catch (Exception e){
         log.error(e);
         throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "documents"));
        }
    }

    @Override
    public Documents update(String fileName, MultipartFile file, Long id) {
        Documents documents = this.getByIdThrowException(id);

        if(Objects.nonNull(file)) {
            byte[] bytes;

            try {
                bytes = file.getInputStream().readAllBytes();
            } catch (IOException e) {
                log.error(e);
                throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "documents"));
            }

            byte[] encoded = Base64.encodeBase64(bytes);
            String encodedString = new String(encoded);

            documents.setDocument(encodedString);
            documents.setContentType(file.getContentType());
        }
        if(Strings.isNotBlank(fileName)) documents.setDocumentName(fileName);

        try {
            return this.documentsRepository.save(documents);
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
