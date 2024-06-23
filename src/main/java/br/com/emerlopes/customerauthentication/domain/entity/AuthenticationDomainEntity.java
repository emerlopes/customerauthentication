package br.com.emerlopes.customerauthentication.domain.entity;

import br.com.emerlopes.customerauthentication.domain.shared.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationDomainEntity {
    private String username;
    private String password;
    private String token;
    private String secret;
    private List<UserRole> roles;
}
