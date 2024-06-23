package br.com.emerlopes.customerauthentication.application.mapper;

import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.infrastructure.database.entity.UserEntity;

public class UserEntityMapper {

    public static UserEntity toEntity(
            final UserDomainEntity userDomainEntity
    ) {
        return new UserEntity()
                .setLogin(userDomainEntity.getLogin())
                .setPass(userDomainEntity.getPassword())
                .setRole(userDomainEntity.getRole());
    }
}
