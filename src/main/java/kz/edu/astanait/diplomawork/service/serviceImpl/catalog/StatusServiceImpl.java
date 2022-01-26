package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.model.catalog.Status;
import kz.edu.astanait.diplomawork.repository.catalog.StatusRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<Status> getAll() {
        return statusRepository.findAll();
    }
}
