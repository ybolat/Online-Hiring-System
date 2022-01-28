package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.model.catalog.Subject;
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
