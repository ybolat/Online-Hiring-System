package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.SyllabusByWeek;

import java.util.List;

public interface SyllabusByWeekService {
    List<SyllabusByWeek> getAllBySyllabusId(Long id);
}
