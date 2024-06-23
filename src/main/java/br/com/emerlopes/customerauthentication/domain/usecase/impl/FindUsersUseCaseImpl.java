package br.com.emerlopes.customerauthentication.domain.usecase.impl;

import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.repository.UserDomainRepository;
import br.com.emerlopes.customerauthentication.domain.usecase.FindUsersUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUsersUseCaseImpl implements FindUsersUseCase {

    final UserDomainRepository userDomainRepository;

    public FindUsersUseCaseImpl(
            final UserDomainRepository userDomainRepository
    ) {
        this.userDomainRepository = userDomainRepository;
    }


    @Override
    public List<UserDomainEntity> execute(
            final UserDomainEntity userDomainEntity
    ) {
        return userDomainRepository.findUsers();
    }
}
