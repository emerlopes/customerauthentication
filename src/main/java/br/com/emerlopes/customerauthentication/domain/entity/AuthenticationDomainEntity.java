package br.com.emerlopes.customerauthentication.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationDomainEntity {
    private String username;
    private String password;
    private String token;
    private String secret;
}
