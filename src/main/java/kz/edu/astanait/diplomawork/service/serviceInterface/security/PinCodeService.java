package kz.edu.astanait.diplomawork.service.serviceInterface.security;

import kz.edu.astanait.diplomawork.model.security.PinCode;

import java.util.Optional;

public interface PinCodeService {

    void create(String email);

    PinCode checkPinCode(Integer pinCode, String email);

    Optional<PinCode> getByUserEmail(String email);

    PinCode getByUserEmailThrowException(String email);
}
