package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.PublicationsDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Publications;
import kz.edu.astanait.diplomawork.model.user.User;
import kz.edu.astanait.diplomawork.repository.hiring.PublicationsRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.PublicationsTypeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.PublicationsService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class PublicationsServiceImpl implements PublicationsService {

    private final PublicationsRepository publicationsRepository;
    private final UserProfessionalInfoService userProfessionalInfoService;

    private final PublicationsTypeService publicationsTypeService;

    @Autowired
    public PublicationsServiceImpl(PublicationsRepository publicationsRepository, UserProfessionalInfoService userProfessionalInfoService, PublicationsTypeService publicationsTypeService) {
        this.publicationsRepository = publicationsRepository;
        this.userProfessionalInfoService = userProfessionalInfoService;
        this.publicationsTypeService = publicationsTypeService;
    }

    @Override
    public List<Publications> getAllByUserProfessionalInfoId(Long id) {
        return this.publicationsRepository.findAllByUserProfessionalInfoId(id);
    }

    @Override
    public Optional<Publications> getById(Long id) {
        return this.publicationsRepository.findById(id);
    }

    @Override
    public Publications getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Publications", "id", id)));
    }

    @Override
    public void create(PublicationsDtoRequest publicationsDtoRequest, Principal principal) {
        Publications publications = new Publications();

        User user = this.userProfessionalInfoService.getByUserEmailThrowException(principal.getName());

        publications.setName(publicationsDtoRequest.getName());
        publications.setLink(publicationsDtoRequest.getLink());
        publications.setPublishedDate(publicationsDtoRequest.getPublishedDate());
        publications.setPublicationsType(this.publicationsTypeService.getByIdThrowException(publicationsDtoRequest.getPublicationTypeId()));
        publications.setUserProfessionalInfo(this.userProfessionalInfoService.getByUserIdThrowException(user.getId()));

        try{
            this.publicationsRepository.save(publications);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "creating", "publications"));
        }

    }

    @Override
    public void update(PublicationsDtoRequest publicationsDtoRequest, Long id) {
        Publications publications = this.getByIdThrowException(id);

        if (Objects.nonNull(publicationsDtoRequest.getName())) publications.setName(publicationsDtoRequest.getName());
        if (Objects.nonNull(publicationsDtoRequest.getLink())) publications.setLink(publicationsDtoRequest.getLink());
        if (Objects.nonNull(publicationsDtoRequest.getPublishedDate())) publications.setPublishedDate(publicationsDtoRequest.getPublishedDate());
        if (Objects.nonNull(publicationsDtoRequest.getPublicationTypeId())) publications.setPublicationsType(this.publicationsTypeService.getByIdThrowException(publicationsDtoRequest.getPublicationTypeId()));

        try{
            this.publicationsRepository.save(publications);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "updating", "publications"));
        }
    }

    @Override
    public void delete(Long id) {
        Publications publications = this.getByIdThrowException(id);

        try{
            this.publicationsRepository.delete(publications);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "deleting", "publications"));
        }
    }
}
