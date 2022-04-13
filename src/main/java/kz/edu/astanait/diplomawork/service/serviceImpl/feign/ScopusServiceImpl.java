package kz.edu.astanait.diplomawork.service.serviceImpl.feign;

import kz.edu.astanait.diplomawork.feignClient.ScopusClient;
import kz.edu.astanait.diplomawork.service.serviceInterface.feign.ScopusService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ScopusServiceImpl implements ScopusService {

    private final ScopusClient scopusClient;

    @Value("${scopus.api.key}")
    private String apiKey;

    @Autowired
    public ScopusServiceImpl(ScopusClient scopusClient) {
        this.scopusClient = scopusClient;
    }

    @Override
    public String searchScopusId(String query) {
        String jsonString = this.scopusClient.searchScopus(query, apiKey);
        JSONObject obj = new JSONObject(jsonString);
        return obj.getJSONObject("search-results").getJSONArray("entry").getJSONObject(0).getString("dc:identifier").replaceAll("SCOPUS_ID:", "");
    }
}
