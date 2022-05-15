package kz.edu.astanait.diplomawork.service.serviceInterface.user;

import kz.edu.astanait.diplomawork.model.user.TeamsAdminCredential;

import java.util.Optional;

public interface TeamsAdminCredentialService {

    Optional<TeamsAdminCredential> getByCommissionEmail(String email);

    TeamsAdminCredential getByCommissionEmailThrowException(String email);
}
