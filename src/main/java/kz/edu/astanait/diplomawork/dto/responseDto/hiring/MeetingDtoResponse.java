package kz.edu.astanait.diplomawork.dto.responseDto.hiring;

import lombok.Data;

@Data
public class MeetingDtoResponse {

    private Long id;

    private String meetingLink;

    private String meetingTitle;

    private String meetingDescription;

    private String startDateTime;

    private String endDateTime;
}
