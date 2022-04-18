package kz.edu.astanait.diplomawork.repository.security;

import kz.edu.astanait.diplomawork.model.security.PinCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationPinCodeRepository extends JpaRepository<PinCode, Long> {
    Optional<PinCode> findByPinCodeAndUserEmail(Integer pinCode, String email);
    Optional<PinCode> findByUserEmail(String email);
}
