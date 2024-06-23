package br.com.emerlopes.customerauthentication.infrastructure.database.service;

import br.com.emerlopes.customerauthentication.application.mapper.UserDomainEntityMapper;
import br.com.emerlopes.customerauthentication.application.mapper.UserEntityMapper;
import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.repository.UserDomainRepository;
import br.com.emerlopes.customerauthentication.infrastructure.database.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDomainService implements UserDomainRepository {

    final UserRepository registerUserRepository;

    public UserDomainService(
            final UserRepository registerUserRepository
    ) {
        this.registerUserRepository = registerUserRepository;
    }

    @Override
    public UserDomainEntity saveUser(
            final UserDomainEntity userDomainEntity
    ) {
        final var encryptedPassword = new BCryptPasswordEncoder().encode(
                userDomainEntity.getPassword());

        userDomainEntity.setPassword(encryptedPassword);

        final var userEntity = UserEntityMapper.toEntity(userDomainEntity);

        return UserDomainEntityMapper.toDomainEntity(registerUserRepository.save(userEntity));
    }

    @Override
    public List<UserDomainEntity> findUsers() {
        final var users = registerUserRepository.findAll();
        return UserDomainEntityMapper.toDomainEntity(users);
    }

    @Override
    public UserDomainEntity findUserByLogin(
            final UserDomainEntity userDomainEntity
    ) {
        final var login = userDomainEntity.getLogin();
        final var userFromDatabase = registerUserRepository.findByLogin(login);

        if (userFromDatabase.isEmpty()) {
            return new UserDomainEntity();
        }

        return new UserDomainEntity()
                .setLogin(userFromDatabase.get().getUsername());

    }

    @Override
    public boolean isNewUser(
            final UserDomainEntity user
    ) {
        final var userFromDatabase = registerUserRepository.findByLogin(user.getLogin());

        return userFromDatabase.isEmpty();
    }

}
