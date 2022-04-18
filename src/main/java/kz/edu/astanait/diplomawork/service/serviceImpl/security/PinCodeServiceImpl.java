package kz.edu.astanait.diplomawork.service.serviceImpl.security;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomException;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.security.PinCode;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.repository.security.RegistrationPinCodeRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.security.PinCodeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import kz.edu.astanait.diplomawork.service.serviceInterface.utils.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class PinCodeServiceImpl implements PinCodeService {

    private final RegistrationPinCodeRepository registrationPinCodeRepository;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public PinCodeServiceImpl(RegistrationPinCodeRepository registrationPinCodeRepository, UserService userService, EmailService emailService) {
        this.registrationPinCodeRepository = registrationPinCodeRepository;
        this.userService = userService;
        this.emailService = emailService;
    }

    @Override
    public Optional<PinCode> getByUserEmail(String email) {
        return this.registrationPinCodeRepository.findByUserEmail(email);
    }

    @Override
    public PinCode getByUserEmailThrowException(String email) {
        return this.getByUserEmail(email)
                .orElseThrow(() -> new CustomNotFoundException(
                        String.format(ExceptionDescription.CustomNotFoundException, "Registration pin code", "users email", email)));
    }

    @Override
    public void create(String email) {
        User user = this.userService.getByEmailThrowException(email);
        Optional<PinCode> registrationPinCode = this.getByUserEmail(email);

        int x = (int) ((Math.random()*((9999-1000)+1))+1000);

        if (registrationPinCode.isPresent()) {
             this.manipulate(registrationPinCode.get(), user, x, true);
        }
        else {
            PinCode newPinCode = new PinCode();
            this.manipulate(newPinCode, user, x, false);
        }
    }

    private void manipulate(PinCode registrationPinCode, User user, int pinCode, boolean exist) {
        if (!exist) registrationPinCode.setUser(user);
        registrationPinCode.setPinCode(pinCode);

        try {
            this.registrationPinCodeRepository.save(registrationPinCode);
            this.emailService.sendVerificationPinCode(user, pinCode);
        } catch (Exception e) {
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "creating", "pin code for registration"));
        }
    }

    @Override
    public PinCode checkPinCode(Integer pinCode, String email) {
        return this.registrationPinCodeRepository.findByPinCodeAndUserEmail(pinCode, email)
                .orElseThrow(() -> new CustomException("Пин код не валидный, попробуйте еще раз."));
    }
}

