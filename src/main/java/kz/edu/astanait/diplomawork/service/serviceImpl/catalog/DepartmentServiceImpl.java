package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.dto.requestDto.catalog.DepartmentDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.catalog.Department;
import kz.edu.astanait.diplomawork.repository.catalog.DepartmentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.DepartmentService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAll() {
        return this.departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getById(Long id) {
        return this.departmentRepository.findById(id);
    }

    @Override
    public Department getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Department", "id", id)));
    }

    @Override
    public void create(DepartmentDtoRequest departmentDtoRequest) {
        Department department = new Department();

        department.setDepartmentName(departmentDtoRequest.getDepartmentName());

        try{
            this.departmentRepository.save(department);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "department"));
        }
    }

    @Override
    public void update(DepartmentDtoRequest departmentDtoRequest, Long id) {
        Department department = this.getByIdThrowException(id);

        if(Strings.isNotBlank(departmentDtoRequest.getDepartmentName())) department.setDepartmentName(departmentDtoRequest.getDepartmentName());

        try {
            this.departmentRepository.save(department);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "department"));
        }
    }

    @Override
    public void delete(Long id) {
        Department department = this.getByIdThrowException(id);

        try {
            this.departmentRepository.delete(department);
        }catch (Exception e) {
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "department"));
        }
    }
}
