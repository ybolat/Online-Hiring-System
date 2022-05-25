package kz.edu.astanait.diplomawork.dto.requestDto.hiring;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MeetingDtoRequest {

    @NotNull(message = "The meeting link was not specified.")
    private String meetingLink;

    @NotNull(message = "The meeting title was not specified.")
    private String meetingTitle;

    @NotNull(message = "The meeting description was not specified.")
    private String meetingDescription;

    @NotNull(message = "The start time is not specified.")
    private String startDateTime;

    @NotNull(message = "End time not specified.")
    private String endDateTime;
}
