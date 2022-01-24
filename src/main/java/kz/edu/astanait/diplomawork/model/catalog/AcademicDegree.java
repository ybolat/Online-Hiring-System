package kz.edu.astanait.diplomawork.model.catalog;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "academic_degree")
@Data
public class AcademicDegree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
}
