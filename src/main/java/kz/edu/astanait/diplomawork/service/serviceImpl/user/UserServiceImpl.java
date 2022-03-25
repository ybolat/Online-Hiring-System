package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import static kz.edu.astanait.diplomawork.exception.ExceptionDescription.*;

import kz.edu.astanait.diplomawork.dto.requestDto.user.UserAuthorizationDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.user.UserRegistrationDtoRequest;
import kz.edu.astanait.diplomawork.dto.responseDto.user.UserDtoResponse;
import kz.edu.astanait.diplomawork.enviroment.JWTEnvironmentBuilder;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.mapper.user.UserMapper;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.repository.user.UserRepository;
import kz.edu.astanait.diplomawork.security.JWTTokenProvider;
import kz.edu.astanait.diplomawork.security.UserPrincipal;
import kz.edu.astanait.diplomawork.service.serviceInterface.security.RegistrationPinCodeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.security.RoleService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
@Log4j2
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final RoleService roleService;
    private final RegistrationPinCodeService registrationPinCodeService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder;
    private final JWTEnvironmentBuilder jwtEnvironmentBuilder;
    private final JWTTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, @Lazy RegistrationPinCodeService registrationPinCodeService, AuthenticationManager authenticationManager, BCryptPasswordEncoder encoder, JWTEnvironmentBuilder jwtEnvironmentBuilder, JWTTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.registrationPinCodeService = registrationPinCodeService;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtEnvironmentBuilder = jwtEnvironmentBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = this.getByEmailThrowException(username);
         return new UserPrincipal(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return this.userRepository.findByEmail(email);
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
        user.setPassword(this.encoder.encode(userRegistrationDtoRequest.getPassword()));
        user.setName(userRegistrationDtoRequest.getName());
        user.setLastname(userRegistrationDtoRequest.getLastname());
        if (Strings.isNotBlank(userRegistrationDtoRequest.getPatronymic())) user.setPatronymic(userRegistrationDtoRequest.getPatronymic());
        user.setPhone(userRegistrationDtoRequest.getPhone());

        User createdUser;
        try{
            user.setRole(this.roleService.getByNameThrowException("ROLE_CHALLENGER"));
            createdUser = this.userRepository.save(user);
        }catch (Exception e){
            log.error(e);
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
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "activating", "user"));
        }
        return createdUser;
    }

    @Override
    public ResponseEntity<UserDtoResponse> authorization(UserAuthorizationDtoRequest authorizationDtoRequest, HttpServletRequest request) {
        String email = authorizationDtoRequest.getEmail().toLowerCase().trim();
        String password = authorizationDtoRequest.getPassword().trim();

        this.authenticate(email, password);

        User user = getByEmailThrowException(email);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        String ipFromClient = jwtTokenProvider.getIpFromClient(request);
        HttpHeaders jwt = getJwtHeader(userPrincipal, ipFromClient);
        UserDtoResponse userDtoResponse = UserMapper.userToDto(user);
        return new ResponseEntity<>(userDtoResponse, jwt, HttpStatus.OK);

    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal, String ipFromClient) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(jwtEnvironmentBuilder.getJWT_TOKEN_HEADER(), jwtTokenProvider.generateToken(userPrincipal, ipFromClient));
        return httpHeaders;
    }


    private void authenticate(String email, String password) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
