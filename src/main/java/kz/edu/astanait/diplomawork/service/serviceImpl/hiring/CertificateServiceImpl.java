package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.CertificateDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomException;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Certificate;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.repository.hiring.CertificateRepository;
import kz.edu.astanait.diplomawork.service.serviceImpl.utils.SecurityUtils;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CertificateService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DocumentsService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.*;

@Service
@Log4j2
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    private final UserProfessionalInfoService userProfessionalInfoService;
    private final DocumentsService documentsService;

    @Autowired
    public CertificateServiceImpl(CertificateRepository certificateRepository, UserProfessionalInfoService userProfessionalInfoService, DocumentsService documentsService) {
        this.certificateRepository = certificateRepository;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.documentsService = documentsService;
    }

    @Override
    public List<Certificate> getAllByUserProfessionalInfoId(Long id) {
        return this.certificateRepository.findAllByUserProfessionalInfoId(id);
    }

    @Override
    public Optional<Certificate> getById(Long id) {
        return this.certificateRepository.findById(id);
    }

    @Override
    public Certificate getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Certificate", "id", id)));
    }

    @Override
    public void create(String fileName, MultipartFile file, Principal principal) {
        Certificate certificate = new Certificate();

        User user = this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName());

        certificate.setUserProfessionalInfo(this.userProfessionalInfoService.getByUserIdThrowException(user.getId()));
        certificate.setDocument(this.documentsService.create(fileName, file));

        try{
            this.certificateRepository.save(certificate);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "certificate"));
        }
    }

    @Override
    public void createAll(HashMap<String, MultipartFile> file, Principal principal) {
        List<Certificate> certificateList = new ArrayList<>();

        User user = this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName());

        file.forEach((k, v) -> {
            Certificate certificate = new Certificate();
            certificate.setUserProfessionalInfo(this.userProfessionalInfoService.getByUserIdThrowException(user.getId()));
            certificate.setDocument(this.documentsService.create(k, v));

            certificateList.add(certificate);
        });


        try {
            this.certificateRepository.saveAll(certificateList);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "certificate list"));
        }
    }

    @Override
    public void update(Long id, String fileName, MultipartFile file, Principal principal) {
        Certificate certificate = this.getByIdThrowException(id);

        SecurityUtils.checkAccessByPrincipal(certificate.getUserProfessionalInfo().getUser().getEmail(), principal);

        if (Strings.isNotBlank(fileName) || Objects.nonNull(file)) certificate.setDocument(this.documentsService.update(fileName, file, certificate.getDocument().getId()));

        try{
            this.certificateRepository.save(certificate);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "certificate"));
        }
    }

    @Override
    public void delete(Long id, Principal principal) {
        Certificate certificate = this.getByIdThrowException(id);

        SecurityUtils.checkAccessByPrincipal(certificate.getUserProfessionalInfo().getUser().getEmail(), principal);

        try{
            this.certificateRepository.delete(certificate);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "certificate"));
        }
    }
}
