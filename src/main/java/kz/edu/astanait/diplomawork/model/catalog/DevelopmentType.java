package kz.edu.astanait.diplomawork.model.catalog;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "development_type")
@Data
public class DevelopmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
}
