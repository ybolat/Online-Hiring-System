package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import static kz.edu.astanait.diplomawork.exception.ExceptionDescription.*;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserRegistrationDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.repository.user.UserRepository;
import kz.edu.astanait.diplomawork.security.UserPrincipal;
import kz.edu.astanait.diplomawork.service.serviceInterface.security.RegistrationPinCodeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.security.RoleService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final RoleService roleService;
    private final RegistrationPinCodeService registrationPinCodeService;

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, RegistrationPinCodeService registrationPinCodeService, AuthenticationManager authenticationManager, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.registrationPinCodeService = registrationPinCodeService;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
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

    @Override
    public User registration(UserRegistrationDtoRequest userRegistrationDtoRequest) {
        User user = new User();

        user.setEmail(userRegistrationDtoRequest.getEmail());
        user.setPassword(encoder.encode(userRegistrationDtoRequest.getPassword()));
        user.setName(userRegistrationDtoRequest.getName());
        user.setLastname(userRegistrationDtoRequest.getLastname());
        if (Strings.isNotBlank(userRegistrationDtoRequest.getPatronymic())) user.setPatronymic(userRegistrationDtoRequest.getPatronymic());
        user.setPhone(userRegistrationDtoRequest.getPhone());

        User createdUser;
        try{
            user.setRole(roleService.getByNameThrowException("ROLE_CHALLENGER"));
            createdUser = this.userRepository.save(user);
        }catch (Exception e){
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "registering", "user"));
        }
        return createdUser;
    }

    public User activate(String email, Integer pinCode) {
        User user = this.registrationPinCodeService.checkPinCode(pinCode, email).getUser();

        user.setActive(true);
        User createdUser;
        try{
            createdUser = this.userRepository.save(user);
        } catch (Exception e) {
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "updating", "user"));
        }
        return createdUser;
    }

    public ResponseEntity<UserDtoResponse> authorization(String email, String password, HttpServletRequest request) {
        return null;
    }

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
