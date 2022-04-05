package kz.edu.astanait.diplomawork.model.hiring;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "documents")
@Data
public class Documents {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document")
    private String document;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "document_name")
    private String documentName;
}
