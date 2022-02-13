package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Syllabus;

import java.util.List;
import java.util.Optional;

public interface SyllabusService {

    List<Syllabus> getAllByUserId(Long id);

    Optional<Syllabus> getById(Long id);

    Syllabus getByIdWithException(Long id);
}
