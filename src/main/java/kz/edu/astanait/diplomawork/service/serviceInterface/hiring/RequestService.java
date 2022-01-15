package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.RequestDtoResponse;
import kz.edu.astanait.diplomawork.model.hiring.Request;

import java.util.List;

public interface RequestService {

    List<Request> getAllRequest();

    List<RequestDtoResponse> getAllRequestDto();
}
