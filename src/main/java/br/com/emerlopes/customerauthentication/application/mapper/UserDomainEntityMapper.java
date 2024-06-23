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
        return UserDomainEntity.builder()
                .id(userEntity.getId())
                .login(userEntity.getUsername())
                .password(userEntity.getPass())
                .role(userEntity.getRole()).build();
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

        return UserDomainEntity.builder()
                .login(registerUserRequestDTO.getUsername())
                .password(registerUserRequestDTO.getPassword())
                .build();
    }

}
