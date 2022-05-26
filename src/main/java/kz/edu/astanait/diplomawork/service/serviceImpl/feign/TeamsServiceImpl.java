package kz.edu.astanait.diplomawork.service.serviceImpl.feign;

import kz.edu.astanait.diplomawork.dto.requestDto.feign.teams.TeamsEventDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.feign.teams.TeamsLoginDtoRequest;
import kz.edu.astanait.diplomawork.dto.requestDto.hiring.MeetingDtoRequest;
import kz.edu.astanait.diplomawork.feignClient.TeamsClient;
import kz.edu.astanait.diplomawork.feignClient.TeamsLoginClient;
import kz.edu.astanait.diplomawork.model.user.TeamsAdminCredential;
import kz.edu.astanait.diplomawork.service.serviceInterface.feign.TeamsService;
import kz.edu.astanait.diplomawork.service.serviceInterface.hiring.MeetingService;
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

    private final MeetingService meetingService;

    @Autowired
    public TeamsServiceImpl(TeamsClient teamsClient, TeamsLoginClient teamsLoginClient, TeamsAdminCredentialService teamsAdminCredentialService, MeetingService meetingService) {
        this.teamsClient = teamsClient;
        this.teamsLoginClient = teamsLoginClient;
        this.teamsAdminCredentialService = teamsAdminCredentialService;
        this.meetingService = meetingService;
    }

    @Override
    public void create(TeamsEventDtoRequest teamsEventDtoRequest) {

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

        Object teams = this.teamsClient.createEvent(token, userPrincipal, body);

        String teamsString = teams.toString();

        int startLink = teamsString.indexOf("joinUrl") + 8;
        int endLink = teamsString.indexOf("cache-control") - 4;
        String link = teamsString.substring(startLink, endLink);

        int startSubject = teamsString.indexOf("subject") + 8;
        int endSubject = teamsString.indexOf("bodyPreview") - 2;
        String subject = teamsString.substring(startSubject, endSubject);

        int startDescription = teamsString.indexOf("<body>") + 8;
        int endDescription = teamsString.indexOf("<br>");
        String description = teamsString.substring(startDescription, endDescription);

        int startSDT = teamsString.indexOf("start={dateTime=") + 16;
        int endSDT = teamsString.indexOf("end={dateTime=") - 25;
        String sdt = teamsString.substring(startSDT, endSDT);

        int startEND = teamsString.indexOf("end={dateTime=") + 14;
        int endEDT = teamsString.indexOf("location={displayName=") - 25;
        String end = teamsString.substring(startEND, endEDT);


        MeetingDtoRequest meetingDtoRequest = new MeetingDtoRequest();
        meetingDtoRequest.setMeetingLink(link);
        meetingDtoRequest.setMeetingTitle(subject);
        meetingDtoRequest.setMeetingDescription(description);
        meetingDtoRequest.setStartDateTime(sdt);
        meetingDtoRequest.setEndDateTime(end);

        this.meetingService.create(meetingDtoRequest);
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
