package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.TeamsAdminCredentialDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.user.TeamsAdminCredential;
import kz.edu.astanait.diplomawork.repository.user.TeamsAdminCredentialRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.CommissionService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.TeamsAdminCredentialService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TeamsAdminCredentialServiceImpl implements TeamsAdminCredentialService {

    private final TeamsAdminCredentialRepository teamsAdminCredentialRepository;
    private final CommissionService commissionService;

    @Autowired
    public TeamsAdminCredentialServiceImpl(TeamsAdminCredentialRepository teamsAdminCredentialRepository, CommissionService commissionService) {
        this.teamsAdminCredentialRepository = teamsAdminCredentialRepository;
        this.commissionService = commissionService;
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

    @Override
    public Optional<TeamsAdminCredential> getById(Long id) {
        return this.teamsAdminCredentialRepository.findById(id);
    }

    @Override
    public TeamsAdminCredential getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException(String.format(ExceptionDescription.CustomNotFoundException, "TeamsAdminCredential", "id", id)));
    }

    @Override
    public void create(TeamsAdminCredentialDtoRequest teamsAdminCredentialDtoRequest){
        TeamsAdminCredential teamsAdminCredential = new TeamsAdminCredential();

        teamsAdminCredential.setDirectoryId(teamsAdminCredentialDtoRequest.getDirectoryId());
        teamsAdminCredential.setGrantType(teamsAdminCredentialDtoRequest.getGrantType());
        teamsAdminCredential.setClientId(teamsAdminCredentialDtoRequest.getClientId());
        teamsAdminCredential.setClientSecret(teamsAdminCredentialDtoRequest.getClientSecret());
        teamsAdminCredential.setCommission(this.commissionService.getByIdThrowException(teamsAdminCredentialDtoRequest.getCommissionId()));

        try{
            this.teamsAdminCredentialRepository.save(teamsAdminCredential);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "teamsAdminCredential"));
        }
    }

    @Override
    public void update(Long id, TeamsAdminCredentialDtoRequest teamsAdminCredentialDtoRequest){
        TeamsAdminCredential teamsAdminCredential = this.getByIdThrowException(id);

        if(Strings.isNotBlank(teamsAdminCredentialDtoRequest.getDirectoryId())) teamsAdminCredential.setDirectoryId(teamsAdminCredentialDtoRequest.getDirectoryId());
        if(Strings.isNotBlank(teamsAdminCredentialDtoRequest.getGrantType())) teamsAdminCredential.setGrantType(teamsAdminCredentialDtoRequest.getGrantType());
        if(Strings.isNotBlank(teamsAdminCredentialDtoRequest.getClientId())) teamsAdminCredential.setClientId(teamsAdminCredentialDtoRequest.getClientId());
        if(Strings.isNotBlank(teamsAdminCredentialDtoRequest.getClientSecret())) teamsAdminCredential.setClientSecret(teamsAdminCredentialDtoRequest.getClientSecret());
        if(Objects.nonNull(teamsAdminCredentialDtoRequest.getCommissionId())) teamsAdminCredential.setCommission(this.commissionService.getByIdThrowException(teamsAdminCredentialDtoRequest.getCommissionId()));

        try {
            this.teamsAdminCredentialRepository.save(teamsAdminCredential);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "teamsAdminCredential"));
        }
    }

    @Override
    public void delete(Long id) {
        TeamsAdminCredential teamsAdminCredential = this.getByIdThrowException(id);

        try {
            this.teamsAdminCredentialRepository.delete(teamsAdminCredential);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "teamsAdminCredential"));
        }
    }
}
