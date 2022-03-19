package kz.edu.astanait.diplomawork.service.serviceInterface.security;

import kz.edu.astanait.diplomawork.model.security.RegistrationPinCode;
public interface RegistrationPinCodeService {

    void create(String email);

    void update(String email);

    RegistrationPinCode checkPinCode(Integer pinCode, String email);

    RegistrationPinCode getByUserEmail(String email);
}
