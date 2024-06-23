package br.com.emerlopes.customerauthentication.domain.usecase.impl;

import br.com.emerlopes.customerauthentication.application.dto.ErrorCode;
import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.exceptions.UserAlreadyExistsException;
import br.com.emerlopes.customerauthentication.domain.repository.UserDomainRepository;
import br.com.emerlopes.customerauthentication.domain.shared.UserRole;
import br.com.emerlopes.customerauthentication.domain.usecase.RegisterAdminUseCase;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class RegisterAdminUseCaseImpl implements RegisterAdminUseCase {

    final UserDomainRepository userDomainRepository;

    public RegisterAdminUseCaseImpl(
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
        userDomainEntity.setRole(UserRole.ADMIN);
    }

    @PostConstruct
    public void init() {
        final var userDomainEntity = UserDomainEntity.builder()
                .login("root")
                .password("root")
                .build();
        execute(userDomainEntity);
    }
}
