package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.dto.requestDto.catalog.SubjectDtoRequest;
import kz.edu.astanait.diplomawork.model.catalog.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    List<Subject> getAll();

    Optional<Subject> getById(Long id);

    Subject getByIdThrowException(Long id);

    void create(SubjectDtoRequest subjectDtoRequest);

    void update(SubjectDtoRequest subjectDtoRequest, Long id);

    void delete(Long id);

    List<Subject> getAllByAcademicDegreeId(Long id);

    List<Subject> getAllByAcademicDegreeIdOrderByTitleEn(Long id);

    List<Subject> getAllByAcademicDegreeIdOrderByTitleRu(Long id);

    List<Subject> getAllByAcademicDegreeIdOrderByTitleKz(Long id);

    List<Subject> getAllByAcademicDegreeIdOrderByTitleEnDesc(Long id);

    List<Subject> getAllByAcademicDegreeIdOrderByTitleRuDesc(Long id);

    List<Subject> getAllByAcademicDegreeIdOrderByTitleKzDesc(Long id);
}
