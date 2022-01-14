package kz.edu.astanait.diplomawork.service.serviceInterface.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.SubjectDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubject();

    List<SubjectDtoResponse> getAllSubjectDto();
}
