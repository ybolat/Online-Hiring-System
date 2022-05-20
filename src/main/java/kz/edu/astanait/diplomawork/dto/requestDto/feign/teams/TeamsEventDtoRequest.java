package kz.edu.astanait.diplomawork.dto.requestDto.feign.teams;

import lombok.Data;

import java.util.List;

@Data
public class TeamsEventDtoRequest {
    private String subject;
    private String comment;
    private String dateTimeStart;
    private String dateTimeFinish;
    private List<String> attendees;
}
