package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.VacancyDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Vacancy;
import kz.edu.astanait.diplomawork.repository.hiring.VacancyRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicTitleService;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.DepartmentService;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.PositionService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.VacancyService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;

    private final DepartmentService departmentService;
    private final AcademicTitleService academicTitleService;
    private final PositionService positionService;

    @Autowired
    public VacancyServiceImpl(VacancyRepository vacancyRepository, DepartmentService departmentService, AcademicTitleService academicTitleService, PositionService positionService) {
        this.vacancyRepository = vacancyRepository;
        this.departmentService = departmentService;
        this.academicTitleService = academicTitleService;
        this.positionService = positionService;
    }

    @Override
    public List<Vacancy> getAll() {
        return this.vacancyRepository.findAll();
    }

    @Override
    public Optional<Vacancy> getById(Long id) {
        return this.vacancyRepository.findById(id);
    }

    @Override
    public Vacancy getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Vacancy", "id", id)));
    }

    @Override
    public void create(VacancyDtoRequest vacancyDtoRequest) {
        Vacancy vacancy = new Vacancy();

        vacancy.setDepartment(this.departmentService.getByIdThrowException(vacancyDtoRequest.getDepartmentId()));
        vacancy.setAcademicTitle(this.academicTitleService.getByIdThrowException(vacancyDtoRequest.getAcademicTitleId()));
        vacancy.setPosition(this.positionService.getByIdThrowException(vacancyDtoRequest.getPositionId()));
        vacancy.setLink_directory(vacancyDtoRequest.getLink_directory());
        vacancy.setStart_date(vacancyDtoRequest.getStart_date());
        vacancy.setFinish_date(vacancyDtoRequest.getFinish_date());
        vacancy.setNumber(vacancyDtoRequest.getNumber());

        try{
            this.vacancyRepository.save(vacancy);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "vacancy"));
        }
    }

    @Override
    public void update(VacancyDtoRequest vacancyDtoRequest, Long id) {
        Vacancy vacancy = this.getByIdThrowException(id);

        if(Objects.nonNull(vacancyDtoRequest.getDepartmentId())) vacancy.setDepartment(this.departmentService.getByIdThrowException(vacancyDtoRequest.getDepartmentId()));
        if(Objects.nonNull(vacancyDtoRequest.getAcademicTitleId())) vacancy.setAcademicTitle(this.academicTitleService.getByIdThrowException(vacancyDtoRequest.getAcademicTitleId()));
        if(Objects.nonNull(vacancyDtoRequest.getPositionId())) vacancy.setPosition(this.positionService.getByIdThrowException(vacancyDtoRequest.getPositionId()));
        if(Strings.isNotBlank(vacancyDtoRequest.getLink_directory())) vacancy.setLink_directory(vacancyDtoRequest.getLink_directory());
        if(Objects.nonNull(vacancyDtoRequest.getStart_date())) vacancy.setStart_date(vacancyDtoRequest.getStart_date());
        if(Objects.nonNull(vacancyDtoRequest.getFinish_date())) vacancy.setFinish_date(vacancyDtoRequest.getFinish_date());
        if(Objects.nonNull(vacancyDtoRequest.getNumber())) vacancy.setNumber(vacancyDtoRequest.getNumber());

        try {
            this.vacancyRepository.save(vacancy);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "vacancy"));
        }
     }

    @Override
    public void delete(Long id) {
        Vacancy vacancy = this.getByIdThrowException(id);

        try{
            this.vacancyRepository.delete(vacancy);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "vacancy"));
        }
    }

    @Override
    public void createAll(List<VacancyDtoRequest> vacancyDtoRequestList){
        List<Vacancy> vacancyList = new ArrayList<>();

        for(VacancyDtoRequest vacancyDtoRequest: vacancyDtoRequestList) {
            Vacancy vacancy = new Vacancy();

            vacancy.setDepartment(this.departmentService.getByIdThrowException(vacancyDtoRequest.getDepartmentId()));
            vacancy.setAcademicTitle(this.academicTitleService.getByIdThrowException(vacancyDtoRequest.getAcademicTitleId()));
            vacancy.setPosition(this.positionService.getByIdThrowException(vacancyDtoRequest.getPositionId()));
            vacancy.setLink_directory(vacancyDtoRequest.getLink_directory());
            vacancy.setStart_date(vacancyDtoRequest.getStart_date());
            vacancy.setFinish_date(vacancyDtoRequest.getFinish_date());
            vacancy.setNumber(vacancyDtoRequest.getNumber());

            vacancyList.add(vacancy);

            try {
                this.vacancyRepository.saveAll(vacancyList);
            }catch (Exception e){
                log.error(e);
                throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "vacancy list"));
            }
        }
    }
}
