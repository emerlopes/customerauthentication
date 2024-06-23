package br.com.emerlopes.customerauthentication.application.entrypoint.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationRequestDTO {
    private String username;
    private String password;
    private String secret;
}
