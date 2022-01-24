package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Intelligence_legal_documents")
@Data
public class Intelligence_legal_documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "document")
    private String document;
}
