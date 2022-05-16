package kz.edu.astanait.diplomawork.service.serviceInterface.feign;

import kz.edu.astanait.diplomawork.dto.requestDto.feign.teams.TeamsEventDtoRequest;

public interface TeamsService {

    Object create(TeamsEventDtoRequest teamsEventDtoRequest);
}
