package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.user.TeamsAdminCredential;
import kz.edu.astanait.diplomawork.repository.user.TeamsAdminCredentialRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.TeamsAdminCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamsAdminCredentialServiceImpl implements TeamsAdminCredentialService {

    private final TeamsAdminCredentialRepository teamsAdminCredentialRepository;

    @Autowired
    public TeamsAdminCredentialServiceImpl(TeamsAdminCredentialRepository teamsAdminCredentialRepository) {
        this.teamsAdminCredentialRepository = teamsAdminCredentialRepository;
    }

    @Override
    public Optional<TeamsAdminCredential> getByCommissionEmail(String email) {
        return this.teamsAdminCredentialRepository.findByCommissionEmail(email);
    }

    @Override
    public TeamsAdminCredential getByCommissionEmailThrowException(String email) {
        return this.getByCommissionEmail(email).
                orElseThrow(() -> new CustomNotFoundException(String.format(
                        ExceptionDescription.CustomNotFoundException, "Teams admin credential", "commission email", email)));
    }
}
