package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import static kz.edu.astanait.diplomawork.exception.ExceptionDescription.*;

import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.repository.user.UserRepository;
import kz.edu.astanait.diplomawork.security.UserPrincipal;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = this.getByEmailThrowException(username);
         return new UserPrincipal(user);
    }


    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByEmailThrowException(String email) {
        return this.getByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format(UsernameNotFoundException, email)));
    }

    @Override
    public Optional<User> getByID(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User getByIdThrowException(Long id) {
        return this.getByID(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(CustomNotFoundException, "User", "id", id)));
    }

}
