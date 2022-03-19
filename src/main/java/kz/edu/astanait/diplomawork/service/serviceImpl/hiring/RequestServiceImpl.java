package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.RequestDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Request;
import kz.edu.astanait.diplomawork.repository.hiring.RequestRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.StatusService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.RequestService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    private final UserService userService;
    private final StatusService statusService;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, UserService userService, StatusService statusService) {
        this.requestRepository = requestRepository;
        this.userService = userService;
        this.statusService = statusService;
    }

    @Override
    public List<Request> getAll() {
        return this.requestRepository.findAll();
    }

    @Override
    public Optional<Request> getById(Long id) {
        return this.requestRepository.findById(id);
    }

    @Override
    public Request getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Request", "id", id)));
    }

    @Override
    public List<Request> getAllByStatus(Long id) {
        return this.requestRepository.findAllByStatusId(id);
    }

    @Override
    public List<Request> getAllOrderByCreatedDate(LocalDateTime createdDate) {
        return this.requestRepository.findAllOrderByCreatedDate(createdDate);
    }

    @Override
    public List<Request> getAllOrderByCreatedDateDesc(LocalDateTime createdDate) {
        return this.requestRepository.findAllOrderByCreatedDateDesc(createdDate);
    }

    @Override
    public void create(RequestDtoRequest requestDtoRequest) {
        Request request = new Request();

        request.setUser(this.userService.getByIdThrowException(requestDtoRequest.getUserId()));
        request.setStatus(this.statusService.getByIdThrowException(requestDtoRequest.getStatusId()));
        request.setBackground(requestDtoRequest.getBackground());
        request.setAdditional(requestDtoRequest.getAdditional());

        try{
            this.requestRepository.save(request);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "request"));
        }
    }

    @Override
    public void update(RequestDtoRequest requestDtoRequest, Long id) {
        Request request = this.getByIdThrowException(id);

        if(Strings.isNotBlank(requestDtoRequest.getBackground())) request.setBackground(requestDtoRequest.getBackground());
        if(Strings.isNotBlank(requestDtoRequest.getAdditional())) request.setAdditional(requestDtoRequest.getAdditional());

        try {
            this.requestRepository.save(request);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "request"));
        }
    }

    @Override
    public void delete(Long id) {
        Request request = this.getByIdThrowException(id);

        try{
            this.requestRepository.delete(request);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "request"));
        }
    }

}
