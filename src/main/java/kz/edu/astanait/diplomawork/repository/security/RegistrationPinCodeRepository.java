package kz.edu.astanait.diplomawork.repository.security;

import kz.edu.astanait.diplomawork.model.security.RegistrationPinCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationPinCodeRepository extends JpaRepository<RegistrationPinCode, Long> {
    Optional<RegistrationPinCode> findByPinCodeAndUserEmail(Integer pinCode, String email);
    Optional<RegistrationPinCode> findByUserEmail(String email);
}
