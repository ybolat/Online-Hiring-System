package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.IntelligenceLegalDocumentDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.repository.hiring.IntelligenceLegalDocumentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.IntelligenceLegalDocumentService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class IntelligenceLegalDocumentServiceImpl implements IntelligenceLegalDocumentService {

    private final IntelligenceLegalDocumentRepository intelligenceLegalDocumentRepository;

    private final UserProfessionalInfoService userProfessionalInfoService;

    @Autowired
    public IntelligenceLegalDocumentServiceImpl(IntelligenceLegalDocumentRepository intelligenceLegalDocumentRepository, UserProfessionalInfoService userProfessionalInfoService) {
        this.intelligenceLegalDocumentRepository = intelligenceLegalDocumentRepository;
        this.userProfessionalInfoService = userProfessionalInfoService;
    }

    @Override
    public List<IntelligenceLegalDocument> getAllByUserProfessionalInfoId(Long id) {
        return this.intelligenceLegalDocumentRepository.findAllByUserProfessionalInfoId(id);
    }

    @Override
    public Optional<IntelligenceLegalDocument> getById(Long id) {
        return this.intelligenceLegalDocumentRepository.findById(id);
    }

    @Override
    public IntelligenceLegalDocument getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Intelligence Legal Document", "id", id)));
    }

    @Override
    public void create(IntelligenceLegalDocumentDtoRequest intelligenceLegalDocumentDtoRequest, Principal principal) {
        IntelligenceLegalDocument intelligenceLegalDocument = new IntelligenceLegalDocument();

        User user = this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName());

        intelligenceLegalDocument.setDocument(intelligenceLegalDocumentDtoRequest.getDocument());
        intelligenceLegalDocument.setUserProfessionalInfo(this.userProfessionalInfoService.getByUserIdThrowException(user.getId()));

        try{
            this.intelligenceLegalDocumentRepository.save(intelligenceLegalDocument);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "intelligenceLegalDocument"));
        }
    }

    @Override
    public void update(IntelligenceLegalDocumentDtoRequest intelligenceLegalDocumentDtoRequest, Long id) {
        IntelligenceLegalDocument intelligenceLegalDocument = this.getByIdThrowException(id);

        if(Strings.isNotBlank(intelligenceLegalDocumentDtoRequest.getDocument())) intelligenceLegalDocument.setDocument(intelligenceLegalDocument.getDocument());

        try{
            this.intelligenceLegalDocumentRepository.save(intelligenceLegalDocument);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
            .format(ExceptionDescription.RepositoryException, "updating", "intelligence legal document"));
        }

    }

    @Override
    public void delete(Long id) {
        IntelligenceLegalDocument intelligenceLegalDocument = this.getByIdThrowException(id);

        try{
            this.intelligenceLegalDocumentRepository.delete(intelligenceLegalDocument);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
            .format(ExceptionDescription.RepositoryException, "deleting", "intelligence legal document"));
        }
    }
}
