package kz.edu.astanait.diplomawork.dto.requestDto.catalog;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDtoRequest {

    @NotNull(message = "Название департамента не было указано.")
    private String departmentName;
}
