package kz.edu.astanait.diplomawork.mapper.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicTitleDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.AcademicTitle;
import org.apache.logging.log4j.util.Strings;

public class AcademicTitleMapper {

    public static AcademicTitleDtoResponse academicTitleToDto(AcademicTitle academicTitle) {
        AcademicTitleDtoResponse academicTitleDtoResponse = new AcademicTitleDtoResponse();
        academicTitleDtoResponse.setId(academicTitle.getId());
        if(Strings.isNotBlank(academicTitle.getTitle())) academicTitleDtoResponse.setTitle(academicTitle.getTitle());
        return academicTitleDtoResponse;
    }
}
