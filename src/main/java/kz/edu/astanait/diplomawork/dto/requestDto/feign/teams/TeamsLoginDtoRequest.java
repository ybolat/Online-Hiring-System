package kz.edu.astanait.diplomawork.dto.requestDto.feign.teams;

import lombok.Data;

@Data
public class TeamsLoginDtoRequest {
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String resource;
}
