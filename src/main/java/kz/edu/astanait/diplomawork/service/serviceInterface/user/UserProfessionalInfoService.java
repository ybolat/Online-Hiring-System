package kz.edu.astanait.diplomawork.service.serviceInterface.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserProfessionalInfoDtoRequest;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;

import java.util.Optional;

public interface UserProfessionalInfoService {

    Optional<UserProfessionalInfo> getById(Long id);

    UserProfessionalInfo getByIdThrowException(Long id);

    Optional<UserProfessionalInfo> getByUserId(Long id);

    UserProfessionalInfo getByUserIdThrowException(Long id);

    void createProfile(UserProfessionalInfoDtoRequest userProfessionalInfoDtoRequest);

    void updateProfile(UserProfessionalInfoDtoRequest userProfessionalInfoDtoRequest, Long id);
}
