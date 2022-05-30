package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Certificate;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface CertificateService {

    List<Certificate> getAllByUserProfessionalInfoId(Long id);

    Optional<Certificate> getById(Long id);

    Certificate getByIdThrowException(Long id);

    List<Certificate> getMyCertificates(Principal principal);

    void create(String fileName, MultipartFile file, Principal principal);

    void createAll(HashMap<String, MultipartFile> file, Principal principal);

    void update(Long id, String fileName, MultipartFile file, Principal principal);

    void delete(Long id, Principal principal);
}
