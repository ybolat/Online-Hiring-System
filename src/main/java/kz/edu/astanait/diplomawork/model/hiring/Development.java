package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.catalog.DevelopmentType;
import kz.edu.astanait.diplomawork.model.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "development")
@Data
public class Development {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "development_type_id")
    private DevelopmentType developmentType;
}
