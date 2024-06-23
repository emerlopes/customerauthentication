package br.com.emerlopes.customerauthentication.domain.shared;

public interface ExecuteArgs<T, J> {
    T execute(J domainObject);
}
