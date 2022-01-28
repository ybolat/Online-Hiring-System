package kz.edu.astanait.diplomawork.repository.hiring;
import kz.edu.astanait.diplomawork.model.hiring.SyllabusByWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusByWeekRepository extends JpaRepository<SyllabusByWeek, Long> {

    List<SyllabusByWeek> findAllBySyllabusId(Long id);
}
