package kz.edu.astanait.diplomawork.model.user;

import kz.edu.astanait.diplomawork.model.hiring.Document;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_document")
@Data
public class UserDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cv")
    private Document cv;

    @ManyToOne
    @JoinColumn(name = "passport")
    private Document passport;

    @ManyToOne
    @JoinColumn(name = "photo")
    private Document photo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
