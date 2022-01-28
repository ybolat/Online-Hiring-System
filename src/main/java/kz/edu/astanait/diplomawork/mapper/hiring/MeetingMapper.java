package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.MeetingDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.CommissionMapper;
import kz.edu.astanait.diplomawork.model.hiring.Meeting;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;
import java.util.stream.Collectors;

public class MeetingMapper {

    public static MeetingDtoResponse meetingToDto(Meeting meeting) {

        MeetingDtoResponse meetingDtoResponse = new MeetingDtoResponse();
        meetingDtoResponse.setId(meeting.getId());
        if(Strings.isNotBlank(meeting.getName())) meetingDtoResponse.setName(meeting.getName());
        if(Strings.isNotBlank(meeting.getLink())) meetingDtoResponse.setLink(meeting.getLink());
        if(Objects.nonNull(meeting.getDateTime())) meetingDtoResponse.setDateTime(meeting.getDateTime());
        if(Objects.nonNull(meeting.getRequest())) meetingDtoResponse.setRequest(RequestMapper.requestToDto(meeting.getRequest()));
        if(Objects.nonNull(meeting.getCommissionList())) meetingDtoResponse.setCommissionList(meeting.getCommissionList()
                .stream().map(CommissionMapper::commissionToDto).collect(Collectors.toList()));
        return meetingDtoResponse;
    }
}
