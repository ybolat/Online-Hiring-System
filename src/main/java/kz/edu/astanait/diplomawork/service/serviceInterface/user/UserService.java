package kz.edu.astanait.diplomawork.service.serviceInterface.user;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserRegistrationDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Certificate;
import kz.edu.astanait.diplomawork.model.user.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByEmail(String email);

    User getByEmailThrowException(String email);

    Optional<User> getByID(Long id);

    User getByIdThrowException(Long id);

    User registration(UserRegistrationDtoRequest userRegistrationDtoRequest);

    User activate(String email, Integer pinCode);
}
