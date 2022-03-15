package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.SyllabusByWeekDtoResponse;
import kz.edu.astanait.diplomawork.model.hiring.SyllabusByWeek;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class SyllabusByWeekMapper {

    public static SyllabusByWeekDtoResponse syllabusByWeekToDto(SyllabusByWeek syllabusByWeek) {
        SyllabusByWeekDtoResponse syllabusByWeekDtoResponse = new SyllabusByWeekDtoResponse();
        syllabusByWeekDtoResponse.setId(syllabusByWeek.getId());
        if(Objects.nonNull(syllabusByWeek.getSyllabus())) syllabusByWeekDtoResponse.setSyllabus(SyllabusMapper.syllabusToDto(syllabusByWeek.getSyllabus()));
        if(Objects.nonNull(syllabusByWeek.getWeekNumber())) syllabusByWeekDtoResponse.setWeekNumber(syllabusByWeek.getWeekNumber());
        if(Strings.isNotBlank(syllabusByWeek.getTitle())) syllabusByWeekDtoResponse.setTitle(syllabusByWeek.getTitle());
        if(Strings.isNotBlank(syllabusByWeek.getDescription())) syllabusByWeek.setDescription(syllabusByWeek.getDescription());
        return syllabusByWeekDtoResponse;
    }
}
