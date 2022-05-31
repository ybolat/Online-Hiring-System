package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByStatusId(Long id);

    List<Request> findAllOrderByCreatedDate(LocalDateTime createdDate);

    Optional<Request> findByUserId(Long id);

    @Query(nativeQuery = true, value = "select * from request order by created_date desc")
    List<Request> findAllOrderByCreatedDateDesc(LocalDateTime createdDate);

    @Query(nativeQuery = true, value = "select count(id) from request where created_date <= :dateTime and status_id = :statusID")
    Double findNumberOfAllAcceptedSince(LocalDateTime dateTime, Long statusID);

    @Query(nativeQuery = true, value = "select count(id) from request where created_date <= :dateTime")
    Double findNumberOfAllSince(LocalDateTime dateTime);

    Optional<Request> findByUserEmail(String email);
}
