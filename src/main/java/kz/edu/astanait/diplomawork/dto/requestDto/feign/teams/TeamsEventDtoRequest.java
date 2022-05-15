package kz.edu.astanait.diplomawork.dto.requestDto.feign.teams;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;

@Data
public class TeamsEventDtoRequest {
    private String subject;
    private String comment;
    private String dateTimeStart;
    private String dateTimeFinish;
    private HashMap<String, String> attendees;
}
