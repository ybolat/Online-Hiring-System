package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.SyllabusByWeekDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.InvalidData;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.SyllabusByWeek;
import kz.edu.astanait.diplomawork.repository.hiring.SyllabusByWeekRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusByWeekService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
public class SyllabusByWeekServiceImpl implements SyllabusByWeekService {

    private final SyllabusByWeekRepository syllabusByWeekRepository;

    private final SyllabusService syllabusService;

    @Autowired
    public SyllabusByWeekServiceImpl(SyllabusByWeekRepository syllabusByWeekRepository, @Lazy SyllabusService syllabusService) {
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
    public void create(List<SyllabusByWeekDtoRequest> syllabusByWeekDtoRequestList) {
        List<SyllabusByWeek> syllabusByWeekList = new ArrayList<>();

        for (SyllabusByWeekDtoRequest syllabusByWeekDtoRequest : syllabusByWeekDtoRequestList) {
            SyllabusByWeek syllabusByWeek = new SyllabusByWeek();

            syllabusByWeek.setSyllabus(this.syllabusService.getByIdThrowException(syllabusByWeekDtoRequest.getSyllabusId()));
            syllabusByWeek.setWeekNumber(syllabusByWeekDtoRequest.getWeekNumber());
            syllabusByWeek.setTitle(syllabusByWeekDtoRequest.getTitle());
            syllabusByWeek.setDescription(syllabusByWeekDtoRequest.getDescription());

            syllabusByWeekList.add(syllabusByWeek);
        }

        try{
            this.syllabusByWeekRepository.saveAll(syllabusByWeekList);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "creating", "syllabus by week"));
        }
    }

    @Override
    public void update(HashMap<Long, SyllabusByWeekDtoRequest> syllabusByWeekDtoRequestHashMap) {
        List<SyllabusByWeek> syllabusByWeekList = new ArrayList<>();
        for (Map.Entry<Long, SyllabusByWeekDtoRequest> set : syllabusByWeekDtoRequestHashMap.entrySet()) {
            SyllabusByWeek syllabusByWeek = this.getByIdThrowException(set.getKey());

            if(Strings.isNotBlank(set.getValue().getDescription())) syllabusByWeek.setDescription(set.getValue().getDescription());
            if(Strings.isNotBlank(set.getValue().getTitle())) syllabusByWeek.setTitle(set.getValue().getTitle());

            syllabusByWeekList.add(syllabusByWeek);
        }

        try {
            this.syllabusByWeekRepository.saveAll(syllabusByWeekList);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "updating", "syllabus by week"));
        }
    }

    @Override
    public void delete(Long id) {
        List<SyllabusByWeek> syllabusByWeekList = this.getAllBySyllabusId(id);

        try{
            this.syllabusByWeekRepository.deleteAll(syllabusByWeekList);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String
                    .format(ExceptionDescription.RepositoryException, "deleting", "syllabus by week"));
        }
    }

    @Override
    public void createAll(List<SyllabusByWeekDtoRequest> syllabusByWeekDtoRequestList){
        if (syllabusByWeekDtoRequestList.size() == 0) throw new InvalidData(ExceptionDescription.InvalidDataException);
        List<SyllabusByWeek> syllabusByWeekList = new ArrayList<>();

        for(SyllabusByWeekDtoRequest syllabusByWeekDtoRequest: syllabusByWeekDtoRequestList){
            SyllabusByWeek syllabusByWeek = new SyllabusByWeek();

            if(Objects.nonNull(syllabusByWeekDtoRequest.getSyllabusId())) syllabusByWeek.setSyllabus(this.syllabusService.getByIdThrowException(syllabusByWeekDtoRequest.getSyllabusId()));
            if(Objects.nonNull(syllabusByWeekDtoRequest.getWeekNumber())) syllabusByWeek.setWeekNumber(syllabusByWeekDtoRequest.getWeekNumber());
            if(Strings.isNotBlank(syllabusByWeekDtoRequest.getTitle())) syllabusByWeek.setTitle(syllabusByWeekDtoRequest.getTitle());
            if(Strings.isNotBlank(syllabusByWeekDtoRequest.getDescription())) syllabusByWeek.setDescription(syllabusByWeekDtoRequest.getDescription());

            syllabusByWeekList.add(syllabusByWeek);
        }

        try {
            this.syllabusByWeekRepository.saveAll(syllabusByWeekList);
        }catch (Exception e){
            log.error(e);
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "syllabusByWeekList"));
        }

    }
}
