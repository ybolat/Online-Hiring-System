package kz.edu.astanait.diplomawork.repository.user;

import kz.edu.astanait.diplomawork.model.user.TeamsAdminCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamsAdminCredentialRepository extends JpaRepository<TeamsAdminCredential, Long> {
    Optional<TeamsAdminCredential> findByCommissionEmail(String email);
}
