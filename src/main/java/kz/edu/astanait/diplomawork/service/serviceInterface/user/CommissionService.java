package kz.edu.astanait.diplomawork.service.serviceInterface.user;

import kz.edu.astanait.diplomawork.model.user.Commission;

import java.util.List;
import java.util.Optional;

public interface CommissionService {

    List<Commission> getAll();

    Optional<Commission> getById(Long id);

    Commission getByIdThrowException(Long id);
}
