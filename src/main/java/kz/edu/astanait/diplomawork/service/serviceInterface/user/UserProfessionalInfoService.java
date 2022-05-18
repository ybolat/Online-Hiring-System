package kz.edu.astanait.diplomawork.service.serviceInterface.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserProfessionalInfoDtoRequest;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;

import java.security.Principal;
import java.util.Optional;

public interface UserProfessionalInfoService {

    Optional<UserProfessionalInfo> getById(Long id);

    UserProfessionalInfo getByIdThrowException(Long id);

    Optional<UserProfessionalInfo> getByUserId(Long id);

    UserProfessionalInfo getByUserIdThrowException(Long id);

    User getByUserEmailThrowException(String email);

    void createProfile(UserProfessionalInfoDtoRequest userProfessionalInfoDtoRequest, Principal principal);

    void updateProfile(UserProfessionalInfoDtoRequest userProfessionalInfoDtoRequest, Long id);
}
