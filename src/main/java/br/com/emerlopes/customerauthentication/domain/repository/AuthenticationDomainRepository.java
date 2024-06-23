package br.com.emerlopes.customerauthentication.domain.repository;


import br.com.emerlopes.customerauthentication.domain.entity.AuthenticationDomainEntity;

public interface AuthenticationDomainRepository {

    AuthenticationDomainEntity getAuthentication(AuthenticationDomainEntity authenticationDomainEntity);
}
