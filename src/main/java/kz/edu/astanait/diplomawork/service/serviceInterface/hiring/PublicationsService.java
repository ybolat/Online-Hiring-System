package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Publications;
import java.util.List;
import java.util.Optional;

public interface PublicationsService {

    List<Publications> getAllByUserProfessionalInfoId(Long id);

    Optional<Publications> getById(Long id);

    Publications getByIdThrowException(Long id);

}
