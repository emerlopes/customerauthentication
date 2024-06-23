package br.com.emerlopes.customerauthentication.domain.repository;


import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;

import java.util.List;

public interface UserDomainRepository {
    UserDomainEntity saveUser(UserDomainEntity user);

    List<UserDomainEntity> findUsers();

    UserDomainEntity findUserByLogin(UserDomainEntity email);
}
