package kz.edu.astanait.diplomawork.model.catalog;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "subject")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "title_ru")
    private String titleRu;

    @Column(name = "title_kz")
    private String titleKz;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "description_ru")
    private String descriptionRu;

    @Column(name = "description_kz")
    private String descriptionKz;

    @Column(name = "code")
    private String code;

    @Column(name = "volume_credits")
    private Integer volumeCredits;
}
