package br.com.emerlopes.customerauthentication.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${spring.security.secret}")
    private String secret;

    public String generateToken(
            final String login
    ) {
        try {
            return JWT.create()
                    .withIssuer("API")
                    .withSubject(login)
                    .withExpiresAt(getExpirationTime())
                    .sign(getAlgorithm(this.secret));

        } catch (JWTCreationException jwtCreationException) {
            throw new RuntimeException("Error creating token", jwtCreationException);
        }
    }

    private Instant getExpirationTime() {
        return Instant.now().plusSeconds(3600);
    }

    public String validateToken(
            final String token
    ) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm(this.secret))
                    .withIssuer("API")
                    .build();
            return verifier.verify(token).getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public String validateToken(
            final String token,
            final String secret
    ) {
        try {

            JWTVerifier verifier = JWT.require(getAlgorithm(secret))
                    .withIssuer("API")
                    .build();
            return verifier.verify(token.replace("Bearer ", "")).getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Algorithm getAlgorithm(
            final String secret
    ) {
        try {
            final var trimmedSecret = secret.trim();
            return Algorithm.HMAC256(trimmedSecret);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid secret format", e);
        }
    }
}
