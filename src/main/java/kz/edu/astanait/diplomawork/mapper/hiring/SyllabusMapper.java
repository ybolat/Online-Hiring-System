package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.SyllabusDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.UserMapper;
import kz.edu.astanait.diplomawork.mapper.catalog.SubjectMapper;
import kz.edu.astanait.diplomawork.model.hiring.Syllabus;

import java.util.Objects;

public class SyllabusMapper {

    public static SyllabusDtoResponse syllabusToDto(Syllabus syllabus) {

        SyllabusDtoResponse syllabusDtoResponse = new SyllabusDtoResponse();
        syllabusDtoResponse.setId(syllabus.getId());
        if(Objects.nonNull(syllabus.getUser())) syllabusDtoResponse.setUser(UserMapper.userToDto(syllabus.getUser()));
        if(Objects.nonNull(syllabus.getSubject())) syllabusDtoResponse.setSubject(SubjectMapper.subjectToDto(syllabus.getSubject()));
        return syllabusDtoResponse;
    }
}
