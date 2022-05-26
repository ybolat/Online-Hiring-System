package kz.edu.astanait.diplomawork.repository.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query(nativeQuery = true, value = "select * from vacancy where finish_date>:localDateTime")
    List<Vacancy> findAllValid(LocalDateTime localDateTime);
}
