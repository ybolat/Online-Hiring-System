package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.hiring.Certificate;
import kz.edu.astanait.diplomawork.repository.hiring.CertificateRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    @Autowired
    public CertificateServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public List<Certificate> getAllByUserId(Long id) {
        return this.certificateRepository.findAllByUserId(id);
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
}
