package br.com.emerlopes.customerauthentication.domain.usecase;

import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.shared.ExecuteArgs;

import java.util.List;

public interface FindUsersUseCase extends ExecuteArgs<List<UserDomainEntity>, UserDomainEntity> {
}
