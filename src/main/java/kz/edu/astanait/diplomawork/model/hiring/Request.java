package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.User;
import kz.edu.astanait.diplomawork.model.catalog.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request")
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "background")
    private String background;

    @Column(name = "experience")
    private String experience;

    @Column(name = "additional")
    private String additional;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

}
