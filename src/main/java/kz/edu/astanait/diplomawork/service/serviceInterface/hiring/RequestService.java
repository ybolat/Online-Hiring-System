package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.RequestDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Request;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RequestService {

    List<Request> getAll();

    Optional<Request> getById(Long id);

    Request getByIdThrowException(Long id);

    List<Request> getAllByStatus(Long id);

    List<Request> getAllOrderByCreatedDate(LocalDateTime createdDate);

    List<Request> getAllOrderByCreatedDateDesc(LocalDateTime createdDate);

    void create(RequestDtoRequest requestDtoRequest, Principal principal);

    void update(RequestDtoRequest requestDtoRequest, Long id);

    void delete(Long id);

}
