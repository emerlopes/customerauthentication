package br.com.emerlopes.customerauthentication.application.entrypoint.rest.dto;

import lombok.Data;

@Data
public class AuthenticationResponseDTO {
    private String token;
}
