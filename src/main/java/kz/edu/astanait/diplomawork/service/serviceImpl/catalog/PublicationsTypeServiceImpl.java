package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.catalog.PublicationsType;
import kz.edu.astanait.diplomawork.repository.catalog.PublicationsTypeRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.PublicationsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationsTypeServiceImpl implements PublicationsTypeService {

    private final PublicationsTypeRepository publicationsTypeRepository;

    @Autowired
    public PublicationsTypeServiceImpl(PublicationsTypeRepository publicationsTypeRepository) {
        this.publicationsTypeRepository = publicationsTypeRepository;
    }

    @Override
    public List<PublicationsType> getAll() {
        return this.publicationsTypeRepository.findAll();
    }

    @Override
    public Optional<PublicationsType> getById(Long id) {
        return this.publicationsTypeRepository.findById(id);
    }

    @Override
    public PublicationsType getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Publications Type", "id", id)));
    }
}
