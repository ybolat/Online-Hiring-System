package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findAllByUserProfessionalInfoId(Long id);
}
