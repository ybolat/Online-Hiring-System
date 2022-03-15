package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.catalog.Subject;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "syllabus")
@Data
public class Syllabus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_professional_info_id")
    private UserProfessionalInfo userProfessionalInfo;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
