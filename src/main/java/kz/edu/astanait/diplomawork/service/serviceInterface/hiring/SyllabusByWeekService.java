package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusByWeekDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.SyllabusByWeek;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface SyllabusByWeekService {

    List<SyllabusByWeek> getAllBySyllabusId(Long id);

    Optional<SyllabusByWeek> getById(Long id);

    SyllabusByWeek getByIdThrowException(Long id);

    void create(List<SyllabusByWeekDtoRequest> syllabusByWeekDtoRequest);

    void update(HashMap<Long, SyllabusByWeekDtoRequest> syllabusByWeekDtoRequestHashMap);

    void delete(Long id);

    void createAll(List<SyllabusByWeekDtoRequest> syllabusByWeekDtoRequestList);
}
