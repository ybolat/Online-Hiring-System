package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.DevelopmentDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.InvalidData;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Development;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import kz.edu.astanait.diplomawork.repository.hiring.DevelopmentRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.DevelopmentTypeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.DevelopmentService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class DevelopmentServiceImpl implements DevelopmentService {

    private final DevelopmentRepository developmentRepository;

    private final UserProfessionalInfoService userProfessionalInfoService;
    private final DevelopmentTypeService developmentTypeService;
    private final UserService userService;

    @Autowired
    public DevelopmentServiceImpl(DevelopmentRepository developmentRepository, UserProfessionalInfoService userProfessionalInfoService, DevelopmentTypeService developmentTypeService, UserService userService) {
        this.developmentRepository = developmentRepository;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.developmentTypeService = developmentTypeService;
        this.userService = userService;
    }

    @Override
    public List<Development> getAllByUserProfessionalInfoId(Long id) {
        return this.developmentRepository.findAllByUserProfessionalInfoId(id);
    }

    @Override
    public Optional<Development> getById(Long id) {
        return this.developmentRepository.findById(id);
    }

    @Override
    public Development getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Development", "id", id)));
    }

    @Override
    public List<Development> getMyDevelopment(Principal principal) {
        return this.developmentRepository.findByUserEmail(principal.getName());
    }

    @Override
    public void create(DevelopmentDtoRequest developmentDtoRequest, Principal principal) {
        Development development = new Development();

        User user = this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName());

        development.setUserProfessionalInfo(this.userProfessionalInfoService.getByUserIdThrowException(user.getId()));
        development.setName(developmentDtoRequest.getName());
        development.setDescription(developmentDtoRequest.getDescription());
        development.setDevelopmentType(this.developmentTypeService.getByIdThrowException(developmentDtoRequest.getDevelopmentTypeId()));

        try {
            this.developmentRepository.save(development);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "development"));
        }
    }

    @Override
    public void update(DevelopmentDtoRequest developmentDtoRequest, Long id) {
        Development development = this.getByIdThrowException(id);

        if(Strings.isNotBlank(developmentDtoRequest.getName())) development.setName(developmentDtoRequest.getName());
        if(Strings.isNotBlank(developmentDtoRequest.getDescription())) development.setDescription(developmentDtoRequest.getDescription());
        if(Objects.nonNull(developmentDtoRequest.getDevelopmentTypeId())) development.setDevelopmentType(this.developmentTypeService.getByIdThrowException(developmentDtoRequest.getDevelopmentTypeId()));

        try{
            this.developmentRepository.save(development);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "development"));
        }
    }

    @Override
    public void delete(Long id) {
        Development development = this.getByIdThrowException(id);

        try{
            this.developmentRepository.delete(development);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "development"));
        }
    }

    @Override
    public void createAll(List<DevelopmentDtoRequest> developmentDtoRequestList, Principal principal) {
        if (developmentDtoRequestList.size() == 0) throw new InvalidData(ExceptionDescription.InvalidDataException);

        List<Development> developmentList = new ArrayList<>();

        User user = this.userService.getByEmailThrowException(principal.getName());
        UserProfessionalInfo userProfessionalInfo = this.userProfessionalInfoService.getByUserIdThrowException(user.getId());

        for(DevelopmentDtoRequest developmentDtoRequest: developmentDtoRequestList){
            Development development = new Development();

            development.setUserProfessionalInfo(userProfessionalInfo);
           if(Strings.isNotBlank(developmentDtoRequest.getName())) development.setName(developmentDtoRequest.getName());
           if(Strings.isNotBlank(developmentDtoRequest.getDescription())) development.setDescription(developmentDtoRequest.getDescription());
           if(Objects.nonNull(developmentDtoRequest.getDevelopmentTypeId())) development.setDevelopmentType(this.developmentTypeService.getByIdThrowException(developmentDtoRequest.getDevelopmentTypeId()));

            developmentList.add(development);

            try{
                this.developmentRepository.saveAll(developmentList);
            }catch (Exception e){
                log.error(e);
                throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating","DevelopmentServiceImpl"));
            }
        }
    }
}
