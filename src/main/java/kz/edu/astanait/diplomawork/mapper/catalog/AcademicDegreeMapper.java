package kz.edu.astanait.diplomawork.mapper.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicDegreeDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.AcademicDegree;
import org.apache.logging.log4j.util.Strings;

public class AcademicDegreeMapper {

    public static AcademicDegreeDtoResponse academicDegreeToDto(AcademicDegree academicDegree) {

        AcademicDegreeDtoResponse academicDegreeDtoResponse = new AcademicDegreeDtoResponse();
        academicDegreeDtoResponse.setId(academicDegree.getId());
        if(Strings.isNotBlank(academicDegree.getTitle())) academicDegreeDtoResponse.setTitle(academicDegree.getTitle());
        return academicDegreeDtoResponse;
    }
}
