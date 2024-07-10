package br.com.emerlopes.customerauthentication.application.entrypoint.rest;

import br.com.emerlopes.customerauthentication.application.entrypoint.rest.dto.AuthenticationRequestDTO;
import br.com.emerlopes.customerauthentication.application.shared.CustomResponseDTO;
import br.com.emerlopes.customerauthentication.application.exceptions.InvalidLoginException;
import br.com.emerlopes.customerauthentication.application.mapper.AuthenticationDomainEntityMapper;
import br.com.emerlopes.customerauthentication.domain.entity.AuthenticationDomainEntity;
import br.com.emerlopes.customerauthentication.domain.usecase.AuthenticationUseCase;
import br.com.emerlopes.customerauthentication.domain.usecase.ValidateTokenUseCase;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final Logger logger = Logger.getLogger(AuthenticationController.class.getName());
    private final AuthenticationUseCase authenticationUseCase;
    private final ValidateTokenUseCase validateTokenUseCase;


    public AuthenticationController(
            final AuthenticationUseCase authenticationUseCase,
            final ValidateTokenUseCase validateTokenUseCase
    ) {
        this.authenticationUseCase = authenticationUseCase;
        this.validateTokenUseCase = validateTokenUseCase;
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(
            final @RequestHeader("Authorization") String token,
            final @RequestBody @Valid AuthenticationRequestDTO authenticationRequestDTO
    ) {

        logger.info("Validating token");

        final var authenticationDomainEntity = AuthenticationDomainEntityMapper.toDomainEntity(token, authenticationRequestDTO);
        final var login = validateTokenUseCase.execute(authenticationDomainEntity);

        logger.info("Token validated");

        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomResponseDTO<AuthenticationDomainEntity>().setData(login));
    }

    @SneakyThrows
    @PostMapping("/login")
    public ResponseEntity<?> login(
            final @RequestBody AuthenticationRequestDTO authenticationRequestDTO
    ) {

        try {

            logger.info("Logging in user");

            final var authenticationDomainEntity = AuthenticationDomainEntityMapper.toDomainEntity(authenticationRequestDTO);
            final var executionResult = authenticationUseCase.execute(authenticationDomainEntity);

            logger.info("User logged in");

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(
                            new CustomResponseDTO<AuthenticationDomainEntity>()
                                    .setData(executionResult)
                    );
        } catch (Throwable e) {
            throw new InvalidLoginException("User or password invalid.");
        }
    }

    @SneakyThrows
    @PostMapping("/token")
    public ResponseEntity<?> getToken(
            final @RequestParam String username,
            final @RequestParam String password
    ) {

        try {

            logger.info("Requesting token for user");

            final var authenticationDomainEntity = AuthenticationDomainEntityMapper.toDomainEntity(
                    username, password
            );

            final var executionResult = authenticationUseCase.execute(authenticationDomainEntity);

            logger.info("Token requested for user");

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(
                            new CustomResponseDTO<AuthenticationDomainEntity>()
                                    .setData(executionResult)
                    );
        } catch (Throwable e) {
            throw new InvalidLoginException("User or password invalid.");
        }
    }

}
