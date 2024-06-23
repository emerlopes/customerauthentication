package br.com.emerlopes.customerauthentication.domain.usecase;

import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.shared.ExecuteArgs;

import java.util.List;

public interface FindUserByLoginUseCase extends ExecuteArgs<UserDomainEntity, UserDomainEntity> {
}
