package kz.edu.astanait.diplomawork.model.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "teams_admin_credential")
@Data
public class TeamsAdminCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "directory_id")
    private String directoryId;

    @Column(name = "grant_type")
    private String grantType;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String client_secret;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Commission commission;
}

