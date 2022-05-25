package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDtoRequest {

    @NotNull(message = "The department name was not specified.")
    private String departmentName;
}
