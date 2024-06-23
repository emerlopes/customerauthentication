package br.com.emerlopes.customerauthentication.domain.entity;

import br.com.emerlopes.customerauthentication.domain.shared.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class UserDomainEntity {
    private String id;
    private String login;
    private String password;
    private UserRole role;
}
