package br.com.emerlopes.customerauthentication.domain.entity;

import br.com.emerlopes.customerauthentication.domain.shared.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDomainEntity {
    private String id;
    private String login;
    private String password;
    private UserRole role;
}
