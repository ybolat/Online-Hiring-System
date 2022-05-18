package kz.edu.astanait.diplomawork.service.serviceInterface.share;

import kz.edu.astanait.diplomawork.dto.responseDto.share.UserDataDtoResponse;

public interface UserDataService {

    UserDataDtoResponse getData(String secretKey, Long id);
}
