package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Meeting;

import java.util.List;
import java.util.Optional;

public interface MeetingService {

    Optional<Meeting> getById(Long id);

    Meeting getByIdThrowException(Long id);
}
