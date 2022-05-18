package kz.edu.astanait.diplomawork.repository.user;

import kz.edu.astanait.diplomawork.model.user.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {

    Optional<Commission> findByEmail(String email);
}
