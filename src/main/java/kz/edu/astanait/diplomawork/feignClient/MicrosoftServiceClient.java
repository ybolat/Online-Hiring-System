package kz.edu.astanait.diplomawork.feignClient;


import kz.edu.astanait.diplomawork.dto.responseDto.feign.MicrosoftAuthDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microsoft", url = "https://tomcat.astanait.edu.kz:8020")
public interface MicrosoftServiceClient {

    @GetMapping("/microsoft-service/checkuserms")
    ResponseEntity<MicrosoftAuthDtoResponse> getUserInfoFromMicrosoft(@RequestParam String codeAuthenticationRequest, @RequestHeader("Authorization") String authorization);

}
