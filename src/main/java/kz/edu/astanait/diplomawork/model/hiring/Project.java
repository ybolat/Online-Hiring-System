package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.model.catalog.ProjectType;
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
    @JoinColumn(name = "user_id")
    private User user;

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
