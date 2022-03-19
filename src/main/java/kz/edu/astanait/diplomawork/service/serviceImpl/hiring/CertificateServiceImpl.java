package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.CertificateDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Certificate;
import kz.edu.astanait.diplomawork.repository.hiring.CertificateRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CertificateService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    private final UserProfessionalInfoService userProfessionalInfoService;

    @Autowired
    public CertificateServiceImpl(CertificateRepository certificateRepository, UserProfessionalInfoService userProfessionalInfoService) {
        this.certificateRepository = certificateRepository;
        this.userProfessionalInfoService = userProfessionalInfoService;
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
    public void create(CertificateDtoRequest certificateDtoRequest) {
        Certificate certificate = new Certificate();

        certificate.setUserProfessionalInfo(this.userProfessionalInfoService.getByIdThrowException(certificateDtoRequest.getUserProfessionalInfoId()));
        certificate.setCertificate(certificate.getCertificate());

        try{
            this.certificateRepository.save(certificate);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "certificate"));
        }
    }

    @Override
    public void update(CertificateDtoRequest certificateDtoRequest, Long id) {
        Certificate certificate = this.getByIdThrowException(id);

        if(Objects.nonNull(certificateDtoRequest.getCertificate())) certificate.setCertificate(certificateDtoRequest.getCertificate());

        try{
            this.certificateRepository.save(certificate);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "certificate"));
        }
    }

    @Override
    public void delete(Long id) {
        Certificate certificate = this.getByIdThrowException(id);

        try{
            this.certificateRepository.save(certificate);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "certificate"));
        }
    }
}
