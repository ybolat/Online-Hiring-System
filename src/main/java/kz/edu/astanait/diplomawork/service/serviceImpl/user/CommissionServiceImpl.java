package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import kz.edu.astanait.diplomawork.repository.user.CommissionRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionServiceImpl implements CommissionService {

    private final CommissionRepository commissionRepository;

    @Autowired
    public CommissionServiceImpl(CommissionRepository commissionRepository) {
        this.commissionRepository = commissionRepository;
    }
}
