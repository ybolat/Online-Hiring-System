package kz.edu.astanait.diplomawork.model.user;

import kz.edu.astanait.diplomawork.model.catalog.AcademicDegree;
import kz.edu.astanait.diplomawork.model.catalog.AcademicTitle;
import kz.edu.astanait.diplomawork.model.catalog.Position;
import kz.edu.astanait.diplomawork.model.hiring.Vacancy;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_professional_info")
@Data
public class UserProfessionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancyId;

    @ManyToOne
    @JoinColumn(name = "academic_degree_id")
    private AcademicDegree academicDegree;

    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    private AcademicTitle academicTitle;

    @Column(name = "scopus_id")
    private String scopus;

    @Column(name = "research_id")
    private String research;

    @Column(name = "google_scholar")
    private String googleScholar;

    @Column(name = "orcid")
    private String orcid;

    @Column(name = "experience")
    private String experience;

    @Column(name = "scientific_interests")
    private String scientificInterests;

    @Column(name = "education")
    private String education;
}
