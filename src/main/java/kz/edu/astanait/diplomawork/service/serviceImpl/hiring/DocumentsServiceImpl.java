package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Document;
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
    public Optional<Document> getById(Long id) {
        return this.documentsRepository.findById(id);
    }

    @Override
    public Document getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> new CustomNotFoundException(
                String.format(ExceptionDescription.CustomNotFoundException, "Document", "id", id)));
    }

    @Override
    public Document create(String fileName, MultipartFile file)   {
        Document document = new Document();

        byte[] bytes;

        try {
            bytes = file.getInputStream().readAllBytes();
        } catch (IOException e) {
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "document"));
        }

        byte[] encoded = Base64.encodeBase64(bytes);
        String encodedString = new String(encoded);

        document.setDocument(encodedString);
        document.setContentType(file.getContentType());
        document.setDocumentName(fileName);

        try {
            return this.documentsRepository.save(document);
        }catch (Exception e){
         log.error(e);
         throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "document"));
        }
    }

    @Override
    public Document update(String fileName, MultipartFile file, Long id) {
        Document document = this.getByIdThrowException(id);

        if(Objects.nonNull(file)) {
            byte[] bytes;

            try {
                bytes = file.getInputStream().readAllBytes();
            } catch (IOException e) {
                log.error(e);
                throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "document"));
            }

            byte[] encoded = Base64.encodeBase64(bytes);
            String encodedString = new String(encoded);

            document.setDocument(encodedString);
            document.setContentType(file.getContentType());
        }
        if(Strings.isNotBlank(fileName)) document.setDocumentName(fileName);

        try {
            return this.documentsRepository.save(document);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "document"));
        }
    }

    @Override
    public void delete(Long id) {
        Document document = this.getByIdThrowException(id);

        try {
            this.documentsRepository.delete(document);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "document"));
        }
    }
}
