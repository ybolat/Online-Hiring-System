package kz.edu.astanait.diplomawork.service.serviceInterface;

import kz.edu.astanait.diplomawork.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByEmail(String email);

    User getByEmailThrowException(String email);


}
