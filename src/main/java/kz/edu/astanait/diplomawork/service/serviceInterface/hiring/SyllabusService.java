package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusByWeekDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Syllabus;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface SyllabusService {

    List<Syllabus> getAllByUserProfessionalInfoId(Long id);

    Optional<Syllabus> getById(Long id);

    Syllabus getByIdThrowException(Long id);

    List<Syllabus> getMySyllabuses(Principal principal);

    void create(SyllabusDtoRequest syllabusDtoRequest, Principal principal);

    void update(SyllabusDtoRequest syllabusDtoRequest, Long id, HashMap<Long, SyllabusByWeekDtoRequest> syllabusByWeekDtoRequestList);

    void delete(Long id);

    void createAll(List<SyllabusDtoRequest> syllabusDtoRequestList, Principal principal);
}
