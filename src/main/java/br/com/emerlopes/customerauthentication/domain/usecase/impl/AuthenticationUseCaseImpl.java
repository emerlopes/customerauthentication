package br.com.emerlopes.customerauthentication.domain.usecase.impl;

import br.com.emerlopes.customerauthentication.domain.entity.AuthenticationDomainEntity;
import br.com.emerlopes.customerauthentication.domain.repository.AuthenticationDomainRepository;
import br.com.emerlopes.customerauthentication.domain.shared.UserRole;
import br.com.emerlopes.customerauthentication.domain.usecase.AuthenticationUseCase;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationDomainRepository authenticationDomainRepository;

    public AuthenticationUseCaseImpl(
            final AuthenticationManager authenticationManager,
            final AuthenticationDomainRepository authenticationDomainRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.authenticationDomainRepository = authenticationDomainRepository;
    }

    @Override
    public AuthenticationDomainEntity execute(
            final AuthenticationDomainEntity authenticationDomainEntity
    ) {
        final var userPassword = new UsernamePasswordAuthenticationToken(
                authenticationDomainEntity.getUsername(),
                authenticationDomainEntity.getPassword()
        );

        final var auth = authenticationManager.authenticate(userPassword);
        final var roles = auth.getAuthorities();

        authenticationDomainEntity.setRoles(
                roles.stream().map(
                        authority -> UserRole.fromRole(authority.getAuthority())
                ).toList()
        );

        return authenticationDomainRepository.getAuthentication(authenticationDomainEntity);

    }
}
