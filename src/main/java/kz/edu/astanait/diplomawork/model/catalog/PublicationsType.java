package kz.edu.astanait.diplomawork.model.catalog;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "publication_type")
@Data

public class PublicationsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
