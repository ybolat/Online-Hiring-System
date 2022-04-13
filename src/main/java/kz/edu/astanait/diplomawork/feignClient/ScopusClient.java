package kz.edu.astanait.diplomawork.feignClient;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://api.elsevier.com", name = "scopus")
@Headers({"Content-Type: application/json"})
public interface ScopusClient {

    @GetMapping("/content/search/scopus")
    String searchScopus(@RequestParam(name = "query") String query, @RequestHeader("X-ELS-APIKey") String apiKey);


}
