package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.CertificateDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Certificate;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface CertificateService {

    List<Certificate> getAllByUserProfessionalInfoId(Long id);

    Optional<Certificate> getById(Long id);

    Certificate getByIdThrowException(Long id);

    void create(CertificateDtoRequest certificateDtoRequest, Principal principal);

    void update(CertificateDtoRequest certificateDtoRequest, Long id);

    void delete(Long id);
}
