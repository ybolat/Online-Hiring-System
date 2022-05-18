package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.catalog.PublicationsType;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publication")
@Data
public class Publications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @ManyToOne
    @JoinColumn(name = "publication_type_id")
    private PublicationsType publicationsType;

    @ManyToOne
    @JoinColumn(name = "user_professional_info_id")
    private UserProfessionalInfo userProfessionalInfo;
}
