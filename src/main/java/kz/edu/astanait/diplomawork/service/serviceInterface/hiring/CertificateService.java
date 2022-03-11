package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Certificate;

import java.util.List;
import java.util.Optional;

public interface CertificateService {

    List<Certificate> getAllByUserId(Long id);

    Optional<Certificate> getById(Long id);

    Certificate getByIdThrowException(Long id);
}
