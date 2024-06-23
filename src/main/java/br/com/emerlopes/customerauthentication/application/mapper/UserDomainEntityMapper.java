package br.com.emerlopes.customerauthentication.application.mapper;

import br.com.emerlopes.customerauthentication.application.entrypoint.rest.dto.RegisterUserRequestDTO;
import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.shared.UserRole;
import br.com.emerlopes.customerauthentication.infrastructure.database.entity.UserEntity;

import java.util.List;

public class UserDomainEntityMapper {

    public static UserDomainEntity toDomainEntity(
            final UserEntity userEntity
    ) {
        return new UserDomainEntity()
                .setId(userEntity.getId())
                .setLogin(userEntity.getUsername())
                .setPassword(userEntity.getPass())
                .setRole(UserRole.valueOf(userEntity.getRole().name()));
    }

    public static List<UserDomainEntity> toDomainEntity(
            final List<UserEntity> userEntities
    ) {
        return userEntities.stream()
                .map(UserDomainEntityMapper::toDomainEntity)
                .toList();
    }

    public static UserDomainEntity toDomainEntity(
            final RegisterUserRequestDTO registerUserRequestDTO
    ) {
        return new UserDomainEntity()
                .setLogin(registerUserRequestDTO.getUsername())
                .setPassword(registerUserRequestDTO.getPassword())
                .setRole(UserRole.valueOf(registerUserRequestDTO.getRole()));
    }

}
