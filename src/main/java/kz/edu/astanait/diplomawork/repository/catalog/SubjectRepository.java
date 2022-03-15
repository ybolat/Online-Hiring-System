package kz.edu.astanait.diplomawork.repository.catalog;

import kz.edu.astanait.diplomawork.model.catalog.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByAcademicDegreeId(Long id);
    List<Subject> findAllByAcademicDegreeIdOrderByTitleEn(Long id);
    List<Subject> findAllByAcademicDegreeIdOrderByTitleRu(Long id);
    List<Subject> findAllByAcademicDegreeIdOrderByTitleKz(Long id);
    List<Subject> findAllByAcademicDegreeIdOrderByTitleEnDesc(Long id);
    List<Subject> findAllByAcademicDegreeIdOrderByTitleRuDesc(Long id);
    List<Subject> findAllByAcademicDegreeIdOrderByTitleKzDesc(Long id);
}
