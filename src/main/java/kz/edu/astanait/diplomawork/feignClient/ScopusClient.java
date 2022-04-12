package kz.edu.astanait.diplomawork.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "https://api.elsevier.com/content/")
public interface ScopusClient {

}
