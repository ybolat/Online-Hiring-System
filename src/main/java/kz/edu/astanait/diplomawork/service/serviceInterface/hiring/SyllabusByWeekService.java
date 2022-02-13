package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusByWeekDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.SyllabusByWeek;

import java.util.List;
import java.util.Optional;

public interface SyllabusByWeekService {
    List<SyllabusByWeek> getAllBySyllabusId(Long id);

    Optional<SyllabusByWeek> getById(Long id);

    SyllabusByWeek getByIdWithException(Long id);

    void create(SyllabusByWeekDtoRequest syllabusByWeekDtoRequest);

    void update(SyllabusByWeekDtoRequest syllabusByWeekDtoRequest, Long id);

    void delete(Long id);

}
