package kz.edu.astanait.diplomawork.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "https://graph.microsoft.com/v1.0/users/")
public interface TeamsClient {

    @PostMapping("{userPrincipalName}/events")
    ResponseEntity<Object> createEvent(@PathVariable(name = "userPrincipalName") String userPrincipalName);
}
