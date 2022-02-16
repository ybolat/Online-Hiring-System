package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusByWeekDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.SyllabusByWeek;
import kz.edu.astanait.diplomawork.repository.hiring.SyllabusByWeekRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusByWeekService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SyllabusByWeekServiceImpl implements SyllabusByWeekService {

    private final SyllabusByWeekRepository syllabusByWeekRepository;

    private final SyllabusServiceImpl syllabusService;

    @Autowired
    public SyllabusByWeekServiceImpl(SyllabusByWeekRepository syllabusByWeekRepository, SyllabusServiceImpl syllabusService) {
        this.syllabusByWeekRepository = syllabusByWeekRepository;
        this.syllabusService = syllabusService;
    }

    @Override
    public List<SyllabusByWeek> getAllBySyllabusId(Long id) {
        return this.syllabusByWeekRepository.findAllBySyllabusId(id);
    }

    @Override
    public Optional<SyllabusByWeek> getById(Long id) {
        return this.syllabusByWeekRepository.findById(id);
    }

    @Override
    public SyllabusByWeek getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Syllabus by week", "id", id)));
    }

    @Override
    public void create(SyllabusByWeekDtoRequest syllabusByWeekDtoRequest) {
        SyllabusByWeek syllabusByWeek = new SyllabusByWeek();

        syllabusByWeek.setSyllabus(this.syllabusService.getByIdThrowException(syllabusByWeekDtoRequest.getSyllabusId()));
        syllabusByWeek.setWeekNumber(syllabusByWeekDtoRequest.getWeekNumber());
        syllabusByWeek.setTitle(syllabusByWeekDtoRequest.getTitle());
        syllabusByWeek.setDescription(syllabusByWeekDtoRequest.getDescription());

        try{
            this.syllabusByWeekRepository.save(syllabusByWeek);
        }catch (Exception e){
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "creating", "syllabus by week"));
        }
    }

    @Override
    public void update(SyllabusByWeekDtoRequest syllabusByWeekDtoRequest, Long id) {
        SyllabusByWeek syllabusByWeek = this.getByIdThrowException(id);

        if (Strings.isNotBlank(syllabusByWeekDtoRequest.getTitle())) syllabusByWeek.setTitle(syllabusByWeekDtoRequest.getTitle());
        if (Strings.isNotBlank(syllabusByWeekDtoRequest.getDescription())) syllabusByWeek.setDescription(syllabusByWeekDtoRequest.getDescription());

        try{
            this.syllabusByWeekRepository.save(syllabusByWeek);
        }catch (Exception e){
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "updating", "syllabus by week"));
        }
    }

    @Override
    public void delete(Long id) {
        SyllabusByWeek syllabusByWeek = this.getByIdThrowException(id);

        try{
            this.syllabusByWeekRepository.delete(syllabusByWeek);
        }catch (Exception e){
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "deleting", "syllabus by week"));
        }
    }

}
