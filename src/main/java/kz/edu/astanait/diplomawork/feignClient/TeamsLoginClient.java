package kz.edu.astanait.diplomawork.feignClient;

import kz.edu.astanait.diplomawork.dto.requestDto.feign.teams.TeamsLoginDtoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://login.microsoft.com", name = "teamsLogin")
public interface TeamsLoginClient {

    @PostMapping(value = "/{directory}/oauth2/token" , consumes = "application/json")
    ResponseEntity<Object> teamsLogin(@PathVariable(name = "directory") String directory,
                                      @RequestBody TeamsLoginDtoRequest dto);
}
