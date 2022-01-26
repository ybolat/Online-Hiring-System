package kz.edu.astanait.diplomawork.model.catalog;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "article_type")
@Data
public class ArticleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
}
