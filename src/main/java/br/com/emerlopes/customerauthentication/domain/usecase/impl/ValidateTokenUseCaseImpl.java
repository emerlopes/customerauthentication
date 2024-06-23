package br.com.emerlopes.customerauthentication.domain.usecase.impl;

import br.com.emerlopes.customerauthentication.domain.entity.AuthenticationDomainEntity;
import br.com.emerlopes.customerauthentication.domain.repository.AuthenticationDomainRepository;
import br.com.emerlopes.customerauthentication.domain.usecase.ValidateTokenUseCase;
import org.springframework.stereotype.Service;

@Service
public class ValidateTokenUseCaseImpl implements ValidateTokenUseCase {

    private final AuthenticationDomainRepository authenticationDomainRepository;

    public ValidateTokenUseCaseImpl(
            final AuthenticationDomainRepository authenticationDomainRepository
    ) {
        this.authenticationDomainRepository = authenticationDomainRepository;
    }

    @Override
    public AuthenticationDomainEntity execute(
            final AuthenticationDomainEntity authenticationDomainEntity
    ) {
        return authenticationDomainRepository.validateToken(authenticationDomainEntity);
    }
}
