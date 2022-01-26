package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.repository.hiring.DevelopmentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevelopmentServiceImpl implements DevelopmentService {

    private final DevelopmentRepository developmentRepository;

    @Autowired
    public DevelopmentServiceImpl(DevelopmentRepository developmentRepository) {
        this.developmentRepository = developmentRepository;
    }
}
