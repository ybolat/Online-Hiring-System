package kz.edu.astanait.diplomawork.service.serviceImpl.feign;

import kz.edu.astanait.diplomawork.dto.requestDto.feign.teams.TeamsEventDtoRequest;
import kz.edu.astanait.diplomawork.feignClient.TeamsClient;
import kz.edu.astanait.diplomawork.service.serviceInterface.feign.TeamsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class TeamsServiceImpl implements TeamsService {

    @Value("${teams.userPrincipal}")
    private String userPrincipal;

    private final TeamsClient teamsClient;

    @Autowired
    public TeamsServiceImpl(TeamsClient teamsClient) {
        this.teamsClient = teamsClient;
    }

    private Object teamsLogin(Principal principal) {

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

        teamsEventDtoRequest.getAttendees().forEach((k,v) -> {

            JSONObject jsonEmailMain = new JSONObject();

            JSONObject jsonEmailAddress = new JSONObject();
            jsonEmailAddress.put("address", k);
            jsonEmailAddress.put("name", v);

            jsonEmailMain.put("emailAddress", jsonEmailAddress);
            jsonEmailMain.put("type", "required");

            jsonAttendeesArray.put(jsonEmailMain);
        });

        json.put("attendees", jsonAttendeesArray);

        json.put("allowNewTimeProposals", "true");

        json.put("isOnlineMeeting", "true");

        json.put("onlineMeetingProvider", "teamsForBusiness");

        String body = json.toString();
        String token = "eyJ0eXAiOiJKV1QiLCJub25jZSI6Ik1LbHh3MVFvcnZsbl9HamM5VmVadlRMT0ZsQXoyR1BONlhpMU9GNUF5RzgiLCJhbGciOiJSUzI1NiIsIng1dCI6ImpTMVhvMU9XRGpfNTJ2YndHTmd2UU8yVnpNYyIsImtpZCI6ImpTMVhvMU9XRGpfNTJ2YndHTmd2UU8yVnpNYyJ9.eyJhdWQiOiJodHRwczovL2dyYXBoLm1pY3Jvc29mdC5jb20iLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC8xNThmMTVmMy04M2UwLTQ5MDYtODI0Yy02OWJkYzUwZDlkNjEvIiwiaWF0IjoxNjUyNTI1MDg3LCJuYmYiOjE2NTI1MjUwODcsImV4cCI6MTY1MjUyODk4NywiYWlvIjoiRTJaZ1lGRGRLZER6N2JwTU1GdnA2MFdIQThwdkF3QT0iLCJhcHBfZGlzcGxheW5hbWUiOiJZZXJhc3N5bF9EaXBsb21hX1dvcmsiLCJhcHBpZCI6ImE0ZTdiNGQ4LTJiOWEtNDVjYy04ZmI1LWZlZDY2MGVkMTg0NSIsImFwcGlkYWNyIjoiMSIsImlkcCI6Imh0dHBzOi8vc3RzLndpbmRvd3MubmV0LzE1OGYxNWYzLTgzZTAtNDkwNi04MjRjLTY5YmRjNTBkOWQ2MS8iLCJpZHR5cCI6ImFwcCIsIm9pZCI6IjRiODJmYTM4LTBjNDktNDk3OC1iYmM2LTMxZGZkYWE0NDM3YSIsInJoIjoiMC5BVHdBOHhXUEZlQ0RCa21DVEdtOXhRMmRZUU1BQUFBQUFBQUF3QUFBQUFBQUFBQThBQUEuIiwicm9sZXMiOlsiQ2FsZW5kYXJzLlJlYWRXcml0ZSJdLCJzdWIiOiI0YjgyZmEzOC0wYzQ5LTQ5NzgtYmJjNi0zMWRmZGFhNDQzN2EiLCJ0ZW5hbnRfcmVnaW9uX3Njb3BlIjoiRVUiLCJ0aWQiOiIxNThmMTVmMy04M2UwLTQ5MDYtODI0Yy02OWJkYzUwZDlkNjEiLCJ1dGkiOiJLa0RoNEJPWUlVeVNKTzFOX3hjSUFBIiwidmVyIjoiMS4wIiwid2lkcyI6WyIwOTk3YTFkMC0wZDFkLTRhY2ItYjQwOC1kNWNhNzMxMjFlOTAiXSwieG1zX3RjZHQiOjE1NjY1MjcyNTl9.dtojCfRF-R1dnhBWrInYipzvWvyyOPMDRdY3M9k4BsulCntxUpyyToDCVMB8eSRA7xSFfsZ198v9ZpGBlqT4mF208SeJAlMFrqk6-I3Yu2nHb6zTwS5VewL4ipjVyCb6AWOiNpKJV4b-8CEOUJ2x9I78qpF8BC4YRHaRrMoKHqKytOer8IoEEl3VczCu6huOiyRV8S5dcvA9IOST8FptpLpc4HpVjpbF7wZzfWweeDFI2hXGoGB-oHp0TmIMZnGY9bcd5svyGNpf-gDDGzfr8zYk9jYu1cpde19nP9ykCv8r73GUMimCiwKONUtwNIa8ArF0-yx1HJGMxZ1669BbJQ";

        return this.teamsClient.createEvent(token, userPrincipal, body);
    }
}
