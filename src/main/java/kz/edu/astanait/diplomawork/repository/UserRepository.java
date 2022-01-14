package kz.edu.astanait.diplomawork.repository;

import kz.edu.astanait.diplomawork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
