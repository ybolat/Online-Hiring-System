package kz.edu.astanait.diplomawork.model;

import kz.edu.astanait.diplomawork.model.security.Role;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "commission")
@Data
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "email")
    private String email;
}
