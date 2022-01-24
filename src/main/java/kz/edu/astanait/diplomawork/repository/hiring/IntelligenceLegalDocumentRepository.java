package kz.edu.astanait.diplomawork.repository.hiring;
import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntelligenceLegalDocumentRepository extends JpaRepository<IntelligenceLegalDocument, Long> {
}
