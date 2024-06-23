package br.com.emerlopes.customerauthentication.domain.usecase.impl;

import br.com.emerlopes.customerauthentication.application.dto.ErrorCode;
import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.exceptions.UserAlreadyExistsException;
import br.com.emerlopes.customerauthentication.domain.repository.UserDomainRepository;
import br.com.emerlopes.customerauthentication.domain.shared.UserRole;
import br.com.emerlopes.customerauthentication.domain.usecase.RegisterUserUseCase;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    final UserDomainRepository userDomainRepository;

    public RegisterUserUseCaseImpl(
            final UserDomainRepository userDomainRepository
    ) {
        this.userDomainRepository = userDomainRepository;
    }

    @Override
    public UserDomainEntity execute(
            final UserDomainEntity userDomainEntity
    ) {
        if (userDomainRepository.isNewUser(userDomainEntity)) {
            setRole(userDomainEntity);

            return userDomainRepository.saveUser(userDomainEntity);
        }

        throw new UserAlreadyExistsException(
                ErrorCode.USER_ALREADY_EXISTS.getCode(),
                ErrorCode.USER_ALREADY_EXISTS.getMessage()
        );
    }

    private void setRole(
            final UserDomainEntity userDomainEntity
    ) {
        userDomainEntity.setRole(UserRole.USER);
    }
}
