package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.catalog.AcademicTitleDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.DepartmentDtoResponse;
import kz.edu.astanait.diplomawork.dto.responseDto.catalog.PositionDtoResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VacancyDtoResponse {

    private Long id;

    private DepartmentDtoResponse department;

    private AcademicTitleDtoResponse academicTitleDtoResponse;

    private PositionDtoResponse position;

    private String link_directory;

    private LocalDateTime start_date;

    private LocalDateTime finish_date;

    private Long number;
}
