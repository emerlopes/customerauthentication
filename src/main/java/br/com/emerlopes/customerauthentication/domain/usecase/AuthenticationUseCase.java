package br.com.emerlopes.customerauthentication.domain.usecase;

import br.com.emerlopes.customerauthentication.domain.entity.AuthenticationDomainEntity;
import br.com.emerlopes.customerauthentication.domain.shared.ExecuteArgs;

public interface AuthenticationUseCase extends ExecuteArgs<AuthenticationDomainEntity, AuthenticationDomainEntity> {
}
