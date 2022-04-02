package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.hiring.Documents;
import kz.edu.astanait.diplomawork.repository.hiring.DocumentsRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
}
