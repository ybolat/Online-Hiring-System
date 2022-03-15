package kz.edu.astanait.diplomawork.service.serviceImpl.catalog;

import kz.edu.astanait.diplomawork.dto.requestDto.catalog.SubjectDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.catalog.Subject;
import kz.edu.astanait.diplomawork.repository.catalog.SubjectRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.AcademicDegreeService;
import kz.edu.astanait.diplomawork.service.serviceInterface.catalog.SubjectService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final AcademicDegreeService academicDegreeService;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, AcademicDegreeService academicDegreeService) {
        this.subjectRepository = subjectRepository;
        this.academicDegreeService = academicDegreeService;
    }

    @Override
    public List<Subject> getAll() {
        return this.subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> getById(Long id) {
        return this.subjectRepository.findById(id);
    }

    @Override
    public Subject getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "subject", "id", id)));
    }

    @Override
    public void create(SubjectDtoRequest subjectDtoRequest) {
        Subject subject = new Subject();

        subject.setTitleEn(subjectDtoRequest.getTitleEn());
        subject.setTitleRu(subjectDtoRequest.getTitleRu());
        subject.setTitleKz(subjectDtoRequest.getTitleKz());
        subject.setDescriptionEn(subjectDtoRequest.getDescriptionEn());
        subject.setDescriptionRu(subjectDtoRequest.getDescriptionRu());
        subject.setDescriptionKz(subjectDtoRequest.getDescriptionKz());
        subject.setCode(subjectDtoRequest.getCode());
        subject.setVolumeCredits(subjectDtoRequest.getVolumeCredits());
        subject.setAcademicDegree(this.academicDegreeService.getByIdThrowException(subjectDtoRequest.getAcademicDegreeId()));

        try{
            this.subjectRepository.save(subject);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "subject"));
        }
    }

    @Override
    public void update(SubjectDtoRequest subjectDtoRequest, Long id) {
        Subject subject = this.getByIdThrowException(id);

        if(Strings.isNotBlank(subjectDtoRequest.getTitleEn())) subject.setTitleEn(subjectDtoRequest.getTitleEn());
        if(Strings.isNotBlank(subjectDtoRequest.getTitleRu())) subject.setTitleRu(subjectDtoRequest.getTitleRu());
        if(Strings.isNotBlank(subjectDtoRequest.getTitleKz())) subject.setTitleKz(subjectDtoRequest.getTitleKz());
        if(Strings.isNotBlank(subjectDtoRequest.getDescriptionEn())) subject.setDescriptionEn(subjectDtoRequest.getDescriptionEn());
        if(Strings.isNotBlank(subjectDtoRequest.getDescriptionRu())) subject.setDescriptionRu(subjectDtoRequest.getDescriptionRu());
        if(Strings.isNotBlank(subjectDtoRequest.getDescriptionKz())) subject.setDescriptionKz(subjectDtoRequest.getDescriptionKz());
        if(Strings.isNotBlank(subjectDtoRequest.getCode())) subject.setCode(subjectDtoRequest.getCode());
        if(Objects.nonNull(subjectDtoRequest.getVolumeCredits())) subject.setVolumeCredits(subjectDtoRequest.getVolumeCredits());
        if(Objects.nonNull(subjectDtoRequest.getAcademicDegreeId())) subject.setAcademicDegree(this.academicDegreeService.getByIdThrowException(subjectDtoRequest.getAcademicDegreeId()));

        try{
            this.subjectRepository.save(subject);
        } catch (Exception e) {
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException,"updating", "subject"));
        }
    }

    @Override
    public void delete(Long id) {
        Subject subject = this.getByIdThrowException(id);

        try{
            this.subjectRepository.delete(subject);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "deleting", "subject"));
        }
    }

    @Override
    public List<Subject> getAllByAcademicDegreeId(Long id) {
        return this.subjectRepository.findAllByAcademicDegreeId(id);
    }

    @Override
    public List<Subject> getAllByAcademicDegreeIdOrderByTitleEn(Long id) {
        return this.subjectRepository.findAllByAcademicDegreeIdOrderByTitleEn(id);
    }

    @Override
    public List<Subject> getAllByAcademicDegreeIdOrderByTitleRu(Long id) {
        return this.subjectRepository.findAllByAcademicDegreeIdOrderByTitleRu(id);
    }

    @Override
    public List<Subject> getAllByAcademicDegreeIdOrderByTitleKz(Long id) {
        return this.subjectRepository.findAllByAcademicDegreeIdOrderByTitleKz(id);
    }

    @Override
    public List<Subject> getAllByAcademicDegreeIdOrderByTitleEnDesc(Long id) {
        return this.subjectRepository.findAllByAcademicDegreeIdOrderByTitleEnDesc(id);
    }

    @Override
    public List<Subject> getAllByAcademicDegreeIdOrderByTitleRuDesc(Long id) {
        return this.subjectRepository.findAllByAcademicDegreeIdOrderByTitleRuDesc(id);
    }

    @Override
    public List<Subject> getAllByAcademicDegreeIdOrderByTitleKzDesc(Long id) {
        return this.subjectRepository.findAllByAcademicDegreeIdOrderByTitleKzDesc(id);
    }
}
