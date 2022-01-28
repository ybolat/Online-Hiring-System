package kz.edu.astanait.diplomawork.repository.hiring;
import kz.edu.astanait.diplomawork.model.hiring.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
