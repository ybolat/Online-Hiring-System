package kz.edu.astanait.diplomawork.service.serviceImpl.feign;

import kz.edu.astanait.diplomawork.dto.requestDto.feign.teams.TeamsEventDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.feign.teams.TeamsLoginDtoRequest;
import kz.edu.astanait.diplomawork.feignClient.TeamsClient;
import kz.edu.astanait.diplomawork.feignClient.TeamsLoginClient;
import kz.edu.astanait.diplomawork.model.user.TeamsAdminCredential;
import kz.edu.astanait.diplomawork.service.serviceInterface.feign.TeamsService;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.TeamsAdminCredentialService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TeamsServiceImpl implements TeamsService {

    @Value("${teams.userPrincipal}")
    private String userPrincipal;

    @Value("${teams.login.resource}")
    private String resource;

    private final TeamsClient teamsClient;
    private final TeamsLoginClient teamsLoginClient;
    private final TeamsAdminCredentialService teamsAdminCredentialService;

    @Autowired
    public TeamsServiceImpl(TeamsClient teamsClient, TeamsLoginClient teamsLoginClient, TeamsAdminCredentialService teamsAdminCredentialService) {
        this.teamsClient = teamsClient;
        this.teamsLoginClient = teamsLoginClient;
        this.teamsAdminCredentialService = teamsAdminCredentialService;
    }

    @Override
    public Object create(TeamsEventDtoRequest teamsEventDtoRequest) {

        JSONObject json = new JSONObject();

        json.put("subject", teamsEventDtoRequest.getSubject());

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("contentType", "HTML");
        jsonBody.put("content", teamsEventDtoRequest.getComment());

        json.put("body", jsonBody);

        JSONObject jsonStart = new JSONObject();
        jsonStart.put("dateTime", teamsEventDtoRequest.getDateTimeStart());
        jsonStart.put("timeZone", "Asia/Almaty");

        json.put("start", jsonStart);

        JSONObject jsonEnd = new JSONObject();
        jsonEnd.put("dateTime", teamsEventDtoRequest.getDateTimeFinish());
        jsonEnd.put("timeZone", "Asia/Almaty");

        json.put("end", jsonEnd);

        JSONArray jsonAttendeesArray = new JSONArray();

        for (String s : teamsEventDtoRequest.getAttendees()){

            JSONObject jsonEmailMain = new JSONObject();

            JSONObject jsonEmailAddress = new JSONObject();
            jsonEmailAddress.put("address", s);

            jsonEmailMain.put("emailAddress", jsonEmailAddress);
            jsonEmailMain.put("type", "required");

            jsonAttendeesArray.put(jsonEmailMain);
        }

        json.put("attendees", jsonAttendeesArray);

        json.put("allowNewTimeProposals", "true");

        json.put("isOnlineMeeting", "true");

        json.put("onlineMeetingProvider", "teamsForBusiness");

        String body = json.toString();

        String token = this.teamsLogin();

        return this.teamsClient.createEvent(token, userPrincipal, body);
    }

    private String teamsLogin() {
        TeamsAdminCredential teamsAdminCredential = this.teamsAdminCredentialService.getByCommissionEmailThrowException(userPrincipal);

        String directoryId = teamsAdminCredential.getDirectoryId();

        TeamsLoginDtoRequest teamsLoginDtoRequest = new TeamsLoginDtoRequest();
        teamsLoginDtoRequest.setClient_id(teamsAdminCredential.getClientId());
        teamsLoginDtoRequest.setGrant_type(teamsAdminCredential.getGrantType());
        teamsLoginDtoRequest.setResource(resource);
        teamsLoginDtoRequest.setClient_secret(teamsAdminCredential.getClientSecret());

        HashMap<String, String> response = this.teamsLoginClient.teamsLogin(directoryId, teamsLoginDtoRequest);
        return response.get("access_token");
    }
}
