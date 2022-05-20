package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.MeetingDtoResponse;
import kz.edu.astanait.diplomawork.model.hiring.Meeting;
import org.apache.logging.log4j.util.Strings;

public class MeetingMapper {

    public static MeetingDtoResponse meetingToDto(Meeting meeting) {
        MeetingDtoResponse meetingDtoResponse = new MeetingDtoResponse();
        meetingDtoResponse.setId(meeting.getId());

        if (Strings.isNotBlank(meeting.getMeetingLink())) meetingDtoResponse.setMeetingLink(meeting.getMeetingLink());
        if (Strings.isNotBlank(meeting.getMeetingDescription())) meetingDtoResponse.setMeetingDescription(meeting.getMeetingDescription());
        if (Strings.isNotBlank(meeting.getStartDateTime())) meetingDtoResponse.setStartDateTime(meeting.getStartDateTime());
        if (Strings.isNotBlank(meeting.getEndDateTime())) meetingDtoResponse.setEndDateTime(meeting.getEndDateTime());
        if (Strings.isNotBlank(meeting.getMeetingTitle())) meetingDtoResponse.setMeetingTitle(meeting.getMeetingTitle());

        return meetingDtoResponse;
    }
}
