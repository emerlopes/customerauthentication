package br.com.emerlopes.customerauthentication.infrastructure.token;

import br.com.emerlopes.customerauthentication.domain.entity.AuthenticationDomainEntity;
import br.com.emerlopes.customerauthentication.domain.repository.AuthenticationDomainRepository;
import br.com.emerlopes.customerauthentication.infrastructure.security.TokenService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationDomainRepository {

    private final TokenService tokenService;

    public AuthenticationServiceImpl(
            final TokenService tokenService
    ) {
        this.tokenService = tokenService;
    }

    @Override
    public AuthenticationDomainEntity getAuthentication(
            final AuthenticationDomainEntity authenticationDomainEntity
    ) {

        final var userDetails = User
                .withUsername(authenticationDomainEntity.getUsername())
                .password(authenticationDomainEntity.getPassword())
                .authorities(String.valueOf(authenticationDomainEntity.getRoles()))
                .build();

        final var token = tokenService.generateToken(userDetails);
        return AuthenticationDomainEntity
                .builder()
                .username(authenticationDomainEntity.getUsername())
                .token(token)
                .build();
    }

    @Override
    public AuthenticationDomainEntity validateToken(
            final AuthenticationDomainEntity authenticationDomainEntity
    ) {
        final var username = tokenService.validateToken(
                authenticationDomainEntity.getToken(),
                authenticationDomainEntity.getSecret()
        );
        return AuthenticationDomainEntity
                .builder()
                .username(username)
                .build();
    }
}
