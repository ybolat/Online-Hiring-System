package kz.edu.astanait.diplomawork.dto.requestDto.feign.teams;

import lombok.Data;

@Data
public class TeamsLoginDtoRequest {
    private String grantType;
    private String clientId;
    private String clientSecret;
    private String resource;
}
