package br.com.emerlopes.customerauthentication.domain.usecase.impl;

import br.com.emerlopes.customerauthentication.domain.entity.AuthenticationDomainEntity;
import br.com.emerlopes.customerauthentication.domain.repository.AuthenticationDomainRepository;
import br.com.emerlopes.customerauthentication.domain.usecase.GenerateAuthenticationUseCase;
import org.springframework.stereotype.Service;

@Service
public class GenerateAuthenticationUseCaseImpl implements GenerateAuthenticationUseCase {

    private final AuthenticationDomainRepository authenticationDomainRepository;

    public GenerateAuthenticationUseCaseImpl(
            final AuthenticationDomainRepository authenticationDomainRepository
    ) {
        this.authenticationDomainRepository = authenticationDomainRepository;
    }

    @Override
    public AuthenticationDomainEntity execute(
            final AuthenticationDomainEntity authenticationDomainEntity
    ) {
        return authenticationDomainRepository.getAuthentication(authenticationDomainEntity);
    }
}
