package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.User;
import lombok.Data;
import org.w3c.dom.Text;

import javax.persistence.*;

@Entity
@Table(name = "certificate")
@Data
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "certificate")
    private String certificate;
}
