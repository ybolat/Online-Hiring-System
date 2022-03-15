package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.hiring.Publications;
import kz.edu.astanait.diplomawork.repository.hiring.PublicationsRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.PublicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationsServiceImpl implements PublicationsService {

    private final PublicationsRepository publicationsRepository;

    @Autowired
    public PublicationsServiceImpl(PublicationsRepository publicationsRepository) {
        this.publicationsRepository = publicationsRepository;
    }

    @Override
    public List<Publications> getAllByUserProfessionalInfoId(Long id) {
        return this.publicationsRepository.findAllByUserProfessionalInfoId(id);
    }

    @Override
    public Optional<Publications> getById(Long id) {
        return this.publicationsRepository.findById(id);
    }

    @Override
    public Publications getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Publications", "id", id)));
    }
}
