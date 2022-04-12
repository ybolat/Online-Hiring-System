package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.MeetingDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.hiring.Meeting;
import kz.edu.astanait.diplomawork.repository.hiring.MeetingRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.MeetingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Override
    public Optional<Meeting> getById(Long id) {
        return this.meetingRepository.findById(id);
    }

    @Override
    public Meeting getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException
                        (String.format(ExceptionDescription.CustomNotFoundException, "Meeting", "id", id)));
    }

    @Override
    public void create(MeetingDtoRequest meetingDtoRequest) {

    }
}
