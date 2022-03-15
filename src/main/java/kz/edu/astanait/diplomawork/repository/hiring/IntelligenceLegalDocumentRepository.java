package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntelligenceLegalDocumentRepository extends JpaRepository<IntelligenceLegalDocument, Long> {
    List<IntelligenceLegalDocument> findAllByUserProfessionalInfoId(long id);
}
