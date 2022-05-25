package kz.edu.astanait.diplomawork.dto.requestDto.feign.teams;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TeamsLoginDtoRequest {

    @NotNull(message = "The grant type was not specified.")
    private String grant_type;

    @NotNull(message = "The client id was not specified.")
    private String client_id;

    @NotNull(message = "The client secret was not specified.")
    private String client_secret;

    @NotNull(message = "The resource was not specified.")
    private String resource;
}
