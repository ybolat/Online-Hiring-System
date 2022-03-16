package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByStatusId(Long id);
    List<Request> findAllOrderByCreatedDate(LocalDateTime createdDate);
    @Query(nativeQuery = true, value = "select * from request order by created_date desc")
    List<Request> findAllOrderByCreatedDateDesc(LocalDateTime createdDate);
}
