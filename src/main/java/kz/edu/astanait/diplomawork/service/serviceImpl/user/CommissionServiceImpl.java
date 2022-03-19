package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.user.Commission;
import kz.edu.astanait.diplomawork.repository.user.CommissionRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.CommissionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CommissionServiceImpl implements CommissionService {

    private final CommissionRepository commissionRepository;

    @Autowired
    public CommissionServiceImpl(CommissionRepository commissionRepository) {
        this.commissionRepository = commissionRepository;
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
}
