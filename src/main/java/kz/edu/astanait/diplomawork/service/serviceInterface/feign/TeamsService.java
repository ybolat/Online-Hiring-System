package kz.edu.astanait.diplomawork.service.serviceInterface.feign;

import kz.edu.astanait.diplomawork.dto.requestDto.feign.teams.TeamsEventDtoRequest;

public interface TeamsService {

    void create(TeamsEventDtoRequest teamsEventDtoRequest);
}
