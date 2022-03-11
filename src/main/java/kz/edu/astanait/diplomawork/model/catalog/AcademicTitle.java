package kz.edu.astanait.diplomawork.model.catalog;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "academic_title")
@Data

public class AcademicTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
}
