package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.catalog.ProjectType;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "project")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_professional_info_id")
    private UserProfessionalInfo userProfessionalInfo;

    @Column(name = "started_date")
    private LocalDate startedDate;

    @Column(name = "finished_date")
    private LocalDate finishedDate;

    @Column(name = "role")
    private String role;

    @Column(name = "sum")
    private Float sum;

    @Column(name = "fund")
    private String fund;

    @ManyToOne
    @JoinColumn(name = "project_type_id")
    private ProjectType projectType;
}
