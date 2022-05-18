package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.catalog.AcademicTitle;
import kz.edu.astanait.diplomawork.model.catalog.Department;
import kz.edu.astanait.diplomawork.model.catalog.Position;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vacancy")
@Data
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    private AcademicTitle academicTitle;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "link_directory")
    private String link_directory;

    @Column(name = "start_date")
    private LocalDateTime start_date;

    @Column(name = "finish_date")
    private LocalDateTime finish_date;

    @Column(name = "number")
    private Long number;
}
