package kz.edu.astanait.diplomawork.service.serviceInterface.user;

import kz.edu.astanait.diplomawork.model.user.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByEmail(String email);

    User getByEmailThrowException(String email);
}
