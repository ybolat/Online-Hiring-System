package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Syllabus;

import java.util.List;

public interface SyllabusService {

    List<Syllabus> getAllByUserId(Long id);
}
