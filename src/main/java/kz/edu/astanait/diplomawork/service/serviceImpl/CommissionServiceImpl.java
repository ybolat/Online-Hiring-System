package kz.edu.astanait.diplomawork.service.serviceImpl;

import kz.edu.astanait.diplomawork.repository.CommissionRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.CommissionService;
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
