package br.com.emerlopes.customerauthentication.application.entrypoint.rest;

import br.com.emerlopes.customerauthentication.application.entrypoint.rest.dto.AuthenticationRequestDTO;
import br.com.emerlopes.customerauthentication.application.entrypoint.rest.dto.RegisterUserRequestDTO;
import br.com.emerlopes.customerauthentication.application.entrypoint.rest.shared.CustomResponseDTO;
import br.com.emerlopes.customerauthentication.application.exceptions.InvalidLoginException;
import br.com.emerlopes.customerauthentication.application.mapper.AuthenticationDomainEntityMapper;
import br.com.emerlopes.customerauthentication.application.mapper.UserDomainEntityMapper;
import br.com.emerlopes.customerauthentication.domain.entity.AuthenticationDomainEntity;
import br.com.emerlopes.customerauthentication.domain.usecase.AuthenticationUseCase;
import br.com.emerlopes.customerauthentication.domain.usecase.GenerateAuthenticationUseCase;
import br.com.emerlopes.customerauthentication.domain.usecase.RegisterUserUseCase;
import br.com.emerlopes.customerauthentication.domain.usecase.ValidateTokenUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final GenerateAuthenticationUseCase generateAuthenticationUseCase;

    private final AuthenticationUseCase authenticationUseCase;

    private final ValidateTokenUseCase validateTokenUseCase;


    final RegisterUserUseCase registerUserUseCase;

    public AuthenticationController(
            final GenerateAuthenticationUseCase generateAuthenticationUseCase,
            final AuthenticationUseCase authenticationUseCase,
            final ValidateTokenUseCase validateTokenUseCase,
            final RegisterUserUseCase registerUserUseCase
    ) {
        this.generateAuthenticationUseCase = generateAuthenticationUseCase;
        this.authenticationUseCase = authenticationUseCase;
        this.validateTokenUseCase = validateTokenUseCase;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(
            final @RequestHeader("Authorization") String token,
            final @RequestBody @Valid AuthenticationRequestDTO authenticationRequestDTO
    ) {
        final var authenticationDomainEntity = AuthenticationDomainEntityMapper.toDomainEntity(token, authenticationRequestDTO);
        final var login = validateTokenUseCase.execute(authenticationDomainEntity);

        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomResponseDTO<AuthenticationDomainEntity>().setData(login));
    }

    @PostMapping
    public ResponseEntity<?> generateToken(
            final @Valid @RequestPart("email") @Email String email
    ) {

        final var authenticationDomainEntity = AuthenticationDomainEntity.builder()
                .username(email)
                .build();

        final var executionResult = generateAuthenticationUseCase.execute(authenticationDomainEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CustomResponseDTO<AuthenticationDomainEntity>().setData(executionResult));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            final @RequestBody @Valid RegisterUserRequestDTO request
    ) {
        final var userDomainEntity = UserDomainEntityMapper.toDomainEntity(request);
        registerUserUseCase.execute(userDomainEntity);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @SneakyThrows
    @PostMapping("/login")
    public ResponseEntity<?> login(
            final @RequestBody AuthenticationRequestDTO authenticationRequestDTO
    ) {

        try {
            final var authenticationDomainEntity = AuthenticationDomainEntityMapper.toDomainEntity(authenticationRequestDTO);
            final var executionResult = authenticationUseCase.execute(authenticationDomainEntity);

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
