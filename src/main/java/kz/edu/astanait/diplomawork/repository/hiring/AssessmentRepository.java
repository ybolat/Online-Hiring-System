package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    List<Assessment> findAllByRequestId(Long id);
}
