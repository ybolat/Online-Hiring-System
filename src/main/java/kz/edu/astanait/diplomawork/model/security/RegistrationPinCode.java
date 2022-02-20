package kz.edu.astanait.diplomawork.model.security;

import kz.edu.astanait.diplomawork.model.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "registration_pin_code")
@Data
public class RegistrationPinCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "pin_code")
    private Integer pinCode;
}
