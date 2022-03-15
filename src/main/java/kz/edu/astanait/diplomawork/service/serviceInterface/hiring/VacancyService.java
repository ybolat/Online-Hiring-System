package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;
import kz.edu.astanait.diplomawork.dto.requestDto.hiring.VacancyDtoRequest;
import kz.edu.astanait.diplomawork.model.hiring.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyService {

    List<Vacancy> getAll();

    Optional<Vacancy> getById(Long id);

    Vacancy getByIdThrowException(Long id);

    void create(VacancyDtoRequest vacancyDtoRequest);

    void update(VacancyDtoRequest vacancyDtoRequest, Long id);

    void delete(Long id);
}
