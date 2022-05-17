package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.VacancyDtoResponse;
import kz.edu.astanait.diplomawork.mapper.catalog.AcademicTitleMapper;
import kz.edu.astanait.diplomawork.mapper.catalog.DepartmentMapper;
import kz.edu.astanait.diplomawork.mapper.catalog.PositionMapper;
import kz.edu.astanait.diplomawork.model.hiring.Vacancy;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class VacancyMapper {

    public static VacancyDtoResponse vacancyToDto(Vacancy vacancy) {
        VacancyDtoResponse vacancyDtoResponse = new VacancyDtoResponse();
        vacancyDtoResponse.setId(vacancy.getId());
        if(Objects.nonNull(vacancy.getDepartment())) vacancyDtoResponse.setDepartment(DepartmentMapper.departmentToDto(vacancy.getDepartment()));
        if(Strings.isNotBlank(vacancy.getLink_directory())) vacancyDtoResponse.setLink_directory(vacancy.getLink_directory());
        if(Objects.nonNull(vacancy.getAcademicTitle())) vacancyDtoResponse.setAcademicTitleDtoResponse(AcademicTitleMapper.academicTitleToDto(vacancy.getAcademicTitle()));
        if(Objects.nonNull(vacancy.getStart_date())) vacancyDtoResponse.setStart_date(vacancy.getStart_date());
        if(Objects.nonNull(vacancy.getFinish_date())) vacancyDtoResponse.setFinish_date(vacancy.getFinish_date());
        if(Objects.nonNull(vacancy.getNumber())) vacancyDtoResponse.setNumber(vacancy.getNumber());
        return vacancyDtoResponse;
    }
}
