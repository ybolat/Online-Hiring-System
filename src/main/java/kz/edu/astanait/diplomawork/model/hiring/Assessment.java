package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.user.Commission;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "assessment")
@Data
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(name = "vote")
    private Boolean vote;
}
