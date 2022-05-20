package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.CommissionAuthorizationDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.user.CommissionDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.user.CommissionDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.user.Commission;
import kz.edu.astanait.diplomawork.repository.user.CommissionRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.security.RoleService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.CommissionService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class CommissionServiceImpl implements CommissionService {

    private final CommissionRepository commissionRepository;

    private final RoleService roleService;

    @Autowired
    public CommissionServiceImpl(CommissionRepository commissionRepository, RoleService roleService) {
        this.commissionRepository = commissionRepository;
        this.roleService = roleService;
    }

    @Override
    public List<Commission> getAll() {
        return this.commissionRepository.findAll();
    }

    @Override
    public Optional<Commission> getById(Long id) {
        return this.commissionRepository.findById(id);
    }

    @Override
    public Commission getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException(String.format(ExceptionDescription.CustomNotFoundException, "Commission", "id", id)));
    }

    @Override
    public Optional<Commission> getByEmail(String email) {
        return this.commissionRepository.findByEmail(email);
    }

    @Override
    public Commission getByEmailThrowException(String email) {
        return this.getByEmail(email)
                .orElseThrow(() -> new CustomNotFoundException(String.format(ExceptionDescription.CustomNotFoundException, "Commission", "email", email)));
    }

    @Override
    public void create(CommissionDtoRequest commissionDtoRequest) {
        Commission commission = new Commission();

        commission.setEmail(commissionDtoRequest.getEmail());
        commission.setRole(this.roleService.getByNameThrowException(commissionDtoRequest.getRole()));

        try{
            this.commissionRepository.save(commission);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "commission"));
        }
    }

    @Override
    public void update(Long id, CommissionDtoRequest commissionDtoRequest) {
        Commission commission = this.getByIdThrowException(id);

        if (Strings.isNotBlank(commissionDtoRequest.getEmail())) commission.setEmail(commissionDtoRequest.getEmail());
        if (Strings.isNotBlank(commissionDtoRequest.getRole())) commission.setRole(this.roleService.getByNameThrowException(commissionDtoRequest.getRole()));

        try {
            this.commissionRepository.save(commission);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "commission"));
        }
    }

    @Override
    public void delete(Long id) {
        Commission commission = this.getByIdThrowException(id);

        try {
            this.commissionRepository.delete(commission);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "commission"));
        }
    }

    @Override
    public ResponseEntity<CommissionDtoResponse> authorization(CommissionAuthorizationDtoRequest commissionAuthorizationDtoRequest, HttpServletRequest request) {
        String email = commissionAuthorizationDtoRequest.getEmail().toLowerCase().trim();

        if (this.getAll().contains(email)) {
            System.out.println("hello world");
        }

        return null;
    }
}
