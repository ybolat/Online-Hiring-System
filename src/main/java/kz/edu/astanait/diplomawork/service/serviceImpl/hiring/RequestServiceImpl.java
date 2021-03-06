package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.RequestDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.InvalidData;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Request;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.repository.hiring.RequestRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.StatusService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class RequestServiceImpl implements kz.edu.astanait.diplomawork.service.serviceInterface.hiring.RequestService {

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
    public Optional<Request> getByUserId(Long id) {
        return this.requestRepository.findByUserId(id);
    }

    @Override
    public Optional<Request> getByUserEmail(Principal principal) {
        return this.requestRepository.findByUserEmail(principal.getName());
    }

    @Override
    public Request getByUserIdThrowException(Long id) {
        return this.getByUserId(id).
                orElseThrow(() -> new CustomNotFoundException
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
    public Double getPercentageOfAcceptedRequestSince(LocalDateTime startDate, Long statusId) {
        Double acceptedRequestNumber = this.requestRepository.findNumberOfAllAcceptedSince(startDate, statusId);
        Double allRequestNumber = this.requestRepository.findNumberOfAllSince(startDate);
        return (acceptedRequestNumber * 100) / allRequestNumber;
    }

    @Override
    public void create(RequestDtoRequest requestDtoRequest, Principal principal) {
        Request request = new Request();

        request.setUser(this.userService.getByEmailThrowException(principal.getName()));
        request.setStatus(this.statusService.getByStatusNameThrowException("Pending"));
        request.setAdditional(requestDtoRequest.getAdditional());
        request.setCreatedDate(requestDtoRequest.getCreatedDate());

        try{
            this.requestRepository.save(request);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "request"));
        }
    }

    @Override
    public void createAll(List<RequestDtoRequest> requestDtoRequestList, Principal principal){
        if (requestDtoRequestList.size() == 0) throw new InvalidData(ExceptionDescription.InvalidDataException);
        List<Request> requestList = new ArrayList<>();

        User user = this.userService.getByEmailThrowException(principal.getName());

        for(RequestDtoRequest requestDtoRequest: requestDtoRequestList){
            Request request = new Request();

            request.setUser(user);
            if(Objects.nonNull(requestDtoRequest.getCreatedDate())) request.setCreatedDate(requestDtoRequest.getCreatedDate());
            if(Strings.isNotBlank(requestDtoRequest.getAdditional())) request.setAdditional(requestDtoRequest.getAdditional());

            requestList.add(request);
        }

        try {
            this.requestRepository.saveAll(requestList);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "request list"));
        }
    }
}
