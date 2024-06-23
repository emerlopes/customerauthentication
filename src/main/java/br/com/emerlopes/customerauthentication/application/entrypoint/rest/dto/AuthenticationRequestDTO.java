package br.com.emerlopes.customerauthentication.application.entrypoint.rest.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String username;
    private String password;
}
