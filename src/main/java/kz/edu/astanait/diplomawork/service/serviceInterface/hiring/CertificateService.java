package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Certificate;

import java.util.List;

public interface CertificateService {

    List<Certificate> getAllByUserId(Long id);
}
