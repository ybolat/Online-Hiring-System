package kz.edu.astanait.diplomawork.repository.catalog;

import kz.edu.astanait.diplomawork.model.catalog.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByStatusName(String statusName);
}
