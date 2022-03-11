package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.hiring.Development;
import kz.edu.astanait.diplomawork.repository.hiring.DevelopmentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevelopmentServiceImpl implements DevelopmentService {

    private final DevelopmentRepository developmentRepository;

    @Autowired
    public DevelopmentServiceImpl(DevelopmentRepository developmentRepository) {
        this.developmentRepository = developmentRepository;
    }

    @Override
    public List<Development> getAllByUserId(Long id) {
        return this.developmentRepository.findAllByUserId(id);
    }

    @Override
    public Optional<Development> getById(Long id) {
        return this.developmentRepository.findById(id);
    }

    @Override
    public Development getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Development", "id", id)));
    }
}
