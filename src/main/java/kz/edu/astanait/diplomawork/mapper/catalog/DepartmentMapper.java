package kz.edu.astanait.diplomawork.mapper.catalog;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.DepartmentDtoResponse;
import kz.edu.astanait.diplomawork.model.catalog.Department;
import org.apache.logging.log4j.util.Strings;

public class DepartmentMapper {

    public static DepartmentDtoResponse departmentToDto(Department department) {

            DepartmentDtoResponse departmentDtoResponse = new DepartmentDtoResponse();
            departmentDtoResponse.setId(department.getId());
            if(Strings.isNotBlank(department.getDepartmentName())) departmentDtoResponse.setDepartmentName(department.getDepartmentName());
            return departmentDtoResponse;
    }
}
