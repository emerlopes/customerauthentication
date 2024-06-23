package br.com.emerlopes.customerauthentication.application.mapper;

import br.com.emerlopes.customerauthentication.application.entrypoint.rest.dto.AuthenticationRequestDTO;
import br.com.emerlopes.customerauthentication.domain.entity.AuthenticationDomainEntity;

public class AuthenticationDomainEntityMapper {

    public static AuthenticationDomainEntity toDomainEntity(
            final AuthenticationRequestDTO authenticationRequestDTO
    ) {
        return AuthenticationDomainEntity.builder()
                .username(authenticationRequestDTO.getUsername())
                .password(authenticationRequestDTO.getPassword())
                .build();
    }

    public static AuthenticationDomainEntity toDomainEntity(
            final String token,
            final AuthenticationRequestDTO authenticationRequestDTO
    ) {
        return AuthenticationDomainEntity.builder()
                .secret(authenticationRequestDTO.getSecret())
                .token(token)
                .build();
    }
}
