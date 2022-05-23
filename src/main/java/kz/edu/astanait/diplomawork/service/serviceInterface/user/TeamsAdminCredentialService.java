package kz.edu.astanait.diplomawork.service.serviceInterface.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.TeamsAdminCredentialDtoRequest;
import kz.edu.astanait.diplomawork.model.user.TeamsAdminCredential;

import java.security.Principal;
import java.util.Optional;

public interface TeamsAdminCredentialService {

    Optional<TeamsAdminCredential> getByCommissionEmail(String email);

    TeamsAdminCredential getByCommissionEmailThrowException(String email);

    Optional<TeamsAdminCredential> getById(Long id);

    TeamsAdminCredential getByIdThrowException(Long id);

    void create(TeamsAdminCredentialDtoRequest teamsAdminCredentialDtoRequest, Principal principal);

    void update(Long id, TeamsAdminCredentialDtoRequest teamsAdminCredentialDtoRequest);

    void delete(Long id);
}
