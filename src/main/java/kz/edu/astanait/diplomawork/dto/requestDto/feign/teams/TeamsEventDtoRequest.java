package kz.edu.astanait.diplomawork.dto.requestDto.feign.teams;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TeamsEventDtoRequest {

    @NotNull(message = "The subject was not specified.")
    private String subject;

    @NotNull(message = "The comment was not specified.")
    private String comment;

    @NotNull(message = "The start time was not specified.")
    private String dateTimeStart;

    @NotNull(message = "The finish time was not specified.")
    private String dateTimeFinish;

    @NotNull(message = "The attendees were not specified.")
    private List<String> attendees;
}
