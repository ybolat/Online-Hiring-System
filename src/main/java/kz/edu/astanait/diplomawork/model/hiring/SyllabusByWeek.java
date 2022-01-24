package kz.edu.astanait.diplomawork.model.hiring;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "syllabus_by_week")
@Data
public class SyllabusByWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "syllabus_id")
    private Syllabus syllabus;

    @Column(name = "week_number")
    private Integer weekNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
