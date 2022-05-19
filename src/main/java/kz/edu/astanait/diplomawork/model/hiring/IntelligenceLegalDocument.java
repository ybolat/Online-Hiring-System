package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "intelligence_legal_document")
@Data
public class IntelligenceLegalDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_professional_info_id")
    private UserProfessionalInfo userProfessionalInfo;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
}
