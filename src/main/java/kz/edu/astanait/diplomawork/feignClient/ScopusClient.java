package kz.edu.astanait.diplomawork.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@FeignClient(url = "http://localhost:8100/api/v1/scopus", name = "scopus")
public interface ScopusClient {

    @GetMapping("/get/information")
    HashMap<Integer, String> getArticleInformation(@RequestParam(name = "id") String id);

    @GetMapping("/get/author")
    HashMap<String, String> getAuthorInformation(@RequestParam(name = "id") String id);
}
