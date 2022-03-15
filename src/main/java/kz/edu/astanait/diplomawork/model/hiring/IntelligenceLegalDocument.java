package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.user.User;
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

    @Column(name = "document")
    private String document;
}
