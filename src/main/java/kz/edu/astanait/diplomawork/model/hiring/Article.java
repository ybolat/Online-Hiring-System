package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "article")
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "apa")
    private String apa;

    @Column(name = "doi")
    private String doi;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "article_type")
    private String articleType;

    @Column(name = "link")
    private String link;
}
