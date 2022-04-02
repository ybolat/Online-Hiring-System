package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "documents")
@Data
public class Documents {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "document")
    private byte[] document;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "document_name")
    private String documentName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
