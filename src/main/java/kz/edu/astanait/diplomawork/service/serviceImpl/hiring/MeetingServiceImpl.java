package kz.edu.astanait.diplomawork.service.serviceImpl.hiring;

import kz.edu.astanait.diplomawork.dto.requestDto.hiring.MeetingDtoRequest;
import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.exception.domain.RepositoryException;
import kz.edu.astanait.diplomawork.model.hiring.Meeting;
import kz.edu.astanait.diplomawork.repository.hiring.MeetingRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return this.getById(id).orElseThrow(
                () -> new CustomNotFoundException(String.format(ExceptionDescription.CustomNotFoundException, "meeting", "id", id)));
    }

    @Override
    public List<Meeting> getAll() {
        return this.meetingRepository.findAll();
    }

    @Override
    public List<Meeting> getAllByDay(String day) {
        return this.meetingRepository.findByStartDateTimeLike(day);
    }

    @Override
    public void create(MeetingDtoRequest meetingDtoRequest) {
        Meeting meeting = new Meeting();

        meeting.setMeetingLink(meetingDtoRequest.getMeetingLink());
        meeting.setMeetingTitle(meetingDtoRequest.getMeetingTitle());
        meeting.setMeetingDescription(meetingDtoRequest.getMeetingDescription());
        meeting.setEndDateTime(meetingDtoRequest.getEndDateTime());
        meeting.setStartDateTime(meetingDtoRequest.getStartDateTime());

        try{
            this.meetingRepository.save(meeting);
        }catch (Exception e){
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "creating", "meeting"));
        }
    }
}
