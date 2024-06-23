package br.com.emerlopes.customerauthentication.domain.usecase;

import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.shared.ExecuteArgs;

public interface RegisterUserUseCase extends ExecuteArgs<UserDomainEntity, UserDomainEntity> {
}
