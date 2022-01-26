package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.RequestDtoResponse;
import kz.edu.astanait.diplomawork.mapper.hiring.RequestMapper;
import kz.edu.astanait.diplomawork.model.hiring.Request;
import kz.edu.astanait.diplomawork.repository.hiring.RequestRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public List<Request> getAllRequest() {
        return requestRepository.findAll();
    }

}
