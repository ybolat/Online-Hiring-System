package kz.edu.astanait.diplomawork.repository;

import kz.edu.astanait.diplomawork.model.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {
}
