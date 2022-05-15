package kz.edu.astanait.diplomawork.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://graph.microsoft.com/v1.0", name = "teams")
public interface TeamsClient {

    @PostMapping(value = "/users/{admin}/events" , consumes = "application/json")
    ResponseEntity<Object> createEvent(@RequestHeader(name = "Authorization") String token,
                                       @PathVariable(name = "admin") String userPrincipalName,
                                       @RequestBody String body);
}
