package kz.edu.astanait.diplomawork.repository.user;

import kz.edu.astanait.diplomawork.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "select * from users inner join request on users.id = request.user_id " +
            "where request.status_id = :id and request.created_date <= :dateTime")
    List<User> findAllAcceptedUsers(LocalDateTime dateTime, Long id);

    @Query(nativeQuery = true, value = "select * from users where users.is_active = ?1")
    List<User> findByActive(Boolean isActive);
}
