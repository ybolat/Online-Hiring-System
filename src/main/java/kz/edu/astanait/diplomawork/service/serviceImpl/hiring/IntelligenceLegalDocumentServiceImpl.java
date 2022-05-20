package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.repository.hiring.IntelligenceLegalDocumentRepository;
import kz.edu.astanait.diplomawork.service.serviceImpl.utils.SecurityUtils;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DocumentsService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.IntelligenceLegalDocumentService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.*;

@Service
@Log4j2
public class IntelligenceLegalDocumentServiceImpl implements IntelligenceLegalDocumentService {

    private final IntelligenceLegalDocumentRepository intelligenceLegalDocumentRepository;

    private final DocumentsService documentsService;
    private final UserProfessionalInfoService userProfessionalInfoService;

    @Autowired
    public IntelligenceLegalDocumentServiceImpl(IntelligenceLegalDocumentRepository intelligenceLegalDocumentRepository, DocumentsService documentsService, UserProfessionalInfoService userProfessionalInfoService) {
        this.intelligenceLegalDocumentRepository = intelligenceLegalDocumentRepository;
        this.documentsService = documentsService;
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
    public void create(String fileName, MultipartFile file, Principal principal) {
        IntelligenceLegalDocument intelligenceLegalDocument = new IntelligenceLegalDocument();

        User user = this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName());

        intelligenceLegalDocument.setUserProfessionalInfo(this.userProfessionalInfoService.getByUserIdThrowException(user.getId()));
        intelligenceLegalDocument.setDocument(this.documentsService.create(fileName, file));

        try{
            this.intelligenceLegalDocumentRepository.save(intelligenceLegalDocument);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "intelligence legal document", "certificate"));
        }
    }

    @Override
    public void update(Long id, String fileName, MultipartFile file, Principal principal) {
        IntelligenceLegalDocument intelligenceLegalDocument = this.getByIdThrowException(id);

        if (Strings.isNotBlank(fileName) || Objects.nonNull(file)) intelligenceLegalDocument.setDocument(this.documentsService.update(fileName, file, intelligenceLegalDocument.getDocument().getId()));

        try{
            this.intelligenceLegalDocumentRepository.save(intelligenceLegalDocument);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
            .format(ExceptionDescription.RepositoryException, "updating", "intelligence legal document"));
        }

    }

    @Override
    public void delete(Long id, Principal principal) {
        IntelligenceLegalDocument intelligenceLegalDocument = this.getByIdThrowException(id);

        SecurityUtils.checkAccessByPrincipal(intelligenceLegalDocument.getUserProfessionalInfo().getUser().getEmail(), principal);

        this.documentsService.delete(intelligenceLegalDocument.getDocument().getId());

        try{
            this.intelligenceLegalDocumentRepository.delete(intelligenceLegalDocument);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
            .format(ExceptionDescription.RepositoryException, "deleting", "intelligence legal document"));
        }
    }

    @Override
    public void createAll(HashMap<String, MultipartFile> file, Principal principal){
        List<IntelligenceLegalDocument> intelligenceLegalDocumentList = new ArrayList<>();

        User user = this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName());

        file.forEach((k, v) -> {
            IntelligenceLegalDocument intelligenceLegalDocument = new IntelligenceLegalDocument();
            intelligenceLegalDocument.setUserProfessionalInfo(this.userProfessionalInfoService.getByUserIdThrowException(user.getId()));
            intelligenceLegalDocument.setDocument(this.documentsService.create(k, v));

            intelligenceLegalDocumentList.add(intelligenceLegalDocument);
        });


        try {
            this.intelligenceLegalDocumentRepository.saveAll(intelligenceLegalDocumentList);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "certificate list"));
        }
    }
}
