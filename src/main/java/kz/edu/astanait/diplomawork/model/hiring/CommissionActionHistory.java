package kz.edu.astanait.diplomawork.model.hiring;

import kz.edu.astanait.diplomawork.model.user.Commission;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "commission_action_history")
@Data
public class CommissionActionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commission_id")
    private Commission commission;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(name = "is_vote")
    private Boolean isVote;
}

