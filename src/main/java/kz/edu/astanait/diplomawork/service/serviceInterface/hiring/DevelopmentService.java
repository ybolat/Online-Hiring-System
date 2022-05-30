package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.DevelopmentDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Development;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface DevelopmentService {

    List<Development> getAllByUserProfessionalInfoId(Long id);

    Optional<Development> getById(Long id);

    Development getByIdThrowException(Long id);

    List<Development> getMyDevelopment(Principal principal);

    void create(DevelopmentDtoRequest developmentDtoRequest, Principal principal);

    void update(DevelopmentDtoRequest developmentDtoRequest, Long id);

    void delete(Long id);

    void createAll(List<DevelopmentDtoRequest> developmentDtoRequestList, Principal principal);
}
