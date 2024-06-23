package br.com.emerlopes.customerauthentication.domain.usecase.impl;

import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.repository.UserDomainRepository;
import br.com.emerlopes.customerauthentication.domain.usecase.FindUserByLoginUseCase;
import br.com.emerlopes.customerauthentication.domain.usecase.FindUsersUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUserByLoginUseCaseImpl implements FindUserByLoginUseCase {

    final UserDomainRepository userDomainRepository;

    public FindUserByLoginUseCaseImpl(
            final UserDomainRepository userDomainRepository
    ) {
        this.userDomainRepository = userDomainRepository;
    }


    @Override
    public UserDomainEntity execute(
            final UserDomainEntity userDomainEntity
    ) {
        return userDomainRepository.findUserByLogin(userDomainEntity);
    }
}
