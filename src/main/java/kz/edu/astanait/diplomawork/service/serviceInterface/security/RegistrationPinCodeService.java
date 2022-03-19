package kz.edu.astanait.diplomawork.service.serviceInterface.security;

import kz.edu.astanait.diplomawork.model.security.RegistrationPinCode;

import java.util.Optional;

public interface RegistrationPinCodeService {

    void create(String email);

    RegistrationPinCode checkPinCode(Integer pinCode, String email);

    Optional<RegistrationPinCode> getByUserEmail(String email);

    RegistrationPinCode getByUserEmailThrowException(String email);
}
