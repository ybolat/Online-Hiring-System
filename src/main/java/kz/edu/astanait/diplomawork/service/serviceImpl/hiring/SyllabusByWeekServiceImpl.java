package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.repository.hiring.SyllabusByWeekRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.SyllabusByWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyllabusByWeekServiceImpl implements SyllabusByWeekService {

    private final SyllabusByWeekRepository syllabusByWeekRepository;

    @Autowired
    public SyllabusByWeekServiceImpl(SyllabusByWeekRepository syllabusByWeekRepository) {
        this.syllabusByWeekRepository = syllabusByWeekRepository;
    }
}
