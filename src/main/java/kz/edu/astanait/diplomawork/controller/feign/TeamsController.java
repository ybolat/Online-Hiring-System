package kz.edu.astanait.diplomawork.controller.feign;

import kz.edu.astanait.diplomawork.dto.requestDto.feign.teams.TeamsEventDtoRequest;
import kz.edu.astanait.diplomawork.service.serviceInterface.feign.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams")
@PreAuthorize("hasRole(' ')")
public class TeamsController {

    private final TeamsService teamsService;

    @Autowired
    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @PostMapping("/create/event")
    public ResponseEntity<HttpStatus> createEvent(@RequestBody TeamsEventDtoRequest teamsEventDtoRequest) {
        teamsService.create(teamsEventDtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
