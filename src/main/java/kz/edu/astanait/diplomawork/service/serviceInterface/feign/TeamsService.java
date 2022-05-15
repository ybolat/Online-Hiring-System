package kz.edu.astanait.diplomawork.service.serviceInterface.feign;

import kz.edu.astanait.diplomawork.dto.requestDto.feign.teams.TeamsEventDtoRequest;

import java.util.Date;
import java.util.HashMap;

public interface TeamsService {

    Object create(TeamsEventDtoRequest teamsEventDtoRequest);
}
