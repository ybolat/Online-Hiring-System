package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;
import kz.edu.astanait.diplomawork.model.hiring.Vacancy;

import java.util.Optional;

public interface VacancyService {

    Optional<Vacancy> getById(Long id);

    Vacancy getByIdThrowException(Long id);
}
