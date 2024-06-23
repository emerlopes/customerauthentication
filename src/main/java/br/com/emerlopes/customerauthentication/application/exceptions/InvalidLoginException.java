package br.com.emerlopes.customerauthentication.application.exceptions;

import javax.naming.AuthenticationException;

public class InvalidLoginException extends AuthenticationException {

    public InvalidLoginException(String message) {
        super(message);
    }
}