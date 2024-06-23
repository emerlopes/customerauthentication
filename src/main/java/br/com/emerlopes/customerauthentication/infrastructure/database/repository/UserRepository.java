package br.com.emerlopes.customerauthentication.infrastructure.database.repository;

import br.com.emerlopes.customerauthentication.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserDetails findByLogin(String login);
}
