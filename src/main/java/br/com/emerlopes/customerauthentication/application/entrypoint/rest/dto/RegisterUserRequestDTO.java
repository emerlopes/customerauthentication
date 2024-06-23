package br.com.emerlopes.customerauthentication.application.entrypoint.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserRequestDTO {
    private String username;
    private String password;

    @NotBlank
    private String role;
}
