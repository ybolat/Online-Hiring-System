package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.IntelligenceLegalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntelligenceLegalDocumentRepository extends JpaRepository<IntelligenceLegalDocument, Long> {
    List<IntelligenceLegalDocument> findAllByUserProfessionalInfoId(long id);

    @Query(nativeQuery = true, value = "select * from intelligence_legal_document inner join user_professional_info on " +
            "intelligence_legal_document.user_professional_info_id = user_professional_info.id inner join users on user_professional_info.user_id = users.id where users.email=:email")
    List<IntelligenceLegalDocument> findByUserEmail(String email);
}
