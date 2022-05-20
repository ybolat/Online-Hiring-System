package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.CommissionActionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommissionActionHistoryRepository extends JpaRepository<CommissionActionHistory, Long> {
    List<CommissionActionHistory> findByRequestId(Long id);

    Optional<CommissionActionHistory> findByCommissionIdAndRequestId(Long cId, Long rId);
}
