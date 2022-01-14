package kz.edu.astanait.diplomawork.mapper.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.SubjectDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.Subject;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class SubjectsMapper {

     public static SubjectDtoResponse subjectToDto(Subject subject) {
        SubjectDtoResponse subjectDtoResponse = new SubjectDtoResponse();
        subjectDtoResponse.setId(subject.getId());
        if (Strings.isNotBlank(subject.getTitleEn())) subjectDtoResponse.setTitleEn(subject.getTitleEn());
        if (Strings.isNotBlank(subject.getTitleRu())) subjectDtoResponse.setTitleRu(subject.getTitleRu());
        if (Strings.isNotBlank(subject.getTitleKz())) subjectDtoResponse.setTitleKz(subject.getTitleKz());
        if (Strings.isNotBlank(subject.getDescriptionEn())) subjectDtoResponse.setDescriptionEn(subject.getDescriptionEn());
        if (Strings.isNotBlank(subject.getDescriptionRu())) subjectDtoResponse.setDescriptionRu(subject.getDescriptionRu());
        if (Strings.isNotBlank(subject.getDescriptionKz())) subjectDtoResponse.setDescriptionKz(subject.getDescriptionKz());
        if (Strings.isNotBlank(subject.getCode())) subjectDtoResponse.setCode(subject.getCode());
        if (Objects.nonNull(subject.getVolumeCredits())) subjectDtoResponse.setVolumeCredits(subject.getVolumeCredits());
        return subjectDtoResponse;
    }
}
