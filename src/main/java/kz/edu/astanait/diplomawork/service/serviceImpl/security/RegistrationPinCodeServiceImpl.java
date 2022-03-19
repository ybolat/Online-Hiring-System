package kz.edu.astanait.diplomawork.service.serviceImpl.security;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomException;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.security.RegistrationPinCode;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.repository.security.RegistrationPinCodeRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.security.RegistrationPinCodeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RegistrationPinCodeServiceImpl implements RegistrationPinCodeService {

    private final RegistrationPinCodeRepository registrationPinCodeRepository;
    private final UserService userService;

    @Autowired
    public RegistrationPinCodeServiceImpl(RegistrationPinCodeRepository registrationPinCodeRepository, UserService userService) {
        this.registrationPinCodeRepository = registrationPinCodeRepository;
        this.userService = userService;
    }

    @Override
    public void create(String email) {
        User user = this.userService.getByEmailThrowException(email);
        RegistrationPinCode registrationPinCode = new RegistrationPinCode();

        int x = (int) ((Math.random()*((9999-1000)+1))+1000);
        registrationPinCode.setUser(user);
        registrationPinCode.setPinCode(x);

        try{
            this.registrationPinCodeRepository.save(registrationPinCode);
        } catch (Exception e) {
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "creating", "pin code for registration"));
        }
    }

    @Override
    public void update(String email) {
        RegistrationPinCode registrationPinCode = this.getByUserEmail(email);

        int x = (int) ((Math.random()*((9999-1000)+1))+1000);
        registrationPinCode.setPinCode(x);

        try{
            this.registrationPinCodeRepository.save(registrationPinCode);
        } catch (Exception e) {
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "updating", "pin code for registration"));
        }
    }

    @Override
    public RegistrationPinCode checkPinCode(Integer pinCode, String email) {
        return this.registrationPinCodeRepository.findByPinCodeAndUserEmail(pinCode, email)
                .orElseThrow(() -> new CustomException("Пин код не валидный, попробуйте еще раз."));
    }

    @Override
    public RegistrationPinCode getByUserEmail(String email) {
        return this.registrationPinCodeRepository.findByUserEmail(email)
                .orElseThrow(() -> new CustomNotFoundException(
                        String.format(ExceptionDescription.CustomNotFoundException, "Registration pin code", "users email", email)));
    }
}

