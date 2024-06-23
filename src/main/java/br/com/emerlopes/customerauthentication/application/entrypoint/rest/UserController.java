package br.com.emerlopes.customerauthentication.application.entrypoint.rest;

import br.com.emerlopes.customerauthentication.application.entrypoint.rest.dto.RegisterUserRequestDTO;
import br.com.emerlopes.customerauthentication.application.shared.CustomResponseDTO;
import br.com.emerlopes.customerauthentication.application.mapper.UserDomainEntityMapper;
import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.usecase.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    final RegisterUserUseCase registerUserUseCase;
    final RegisterGuestUseCase registerGuestUseCase;
    final RegisterAdminUseCase registerAdminUseCase;
    final FindUsersUseCase findUsersUseCase;
    final FindUserByLoginUseCase findUserByLoginUseCase;

    public UserController(
            final RegisterUserUseCase registerUserUseCase,
            final RegisterGuestUseCase registerGuestUseCase,
            final RegisterAdminUseCase registerAdminUseCase,
            final FindUsersUseCase findUsersUseCase,
            final FindUserByLoginUseCase findUserByLoginUseCase
    ) {
        this.registerUserUseCase = registerUserUseCase;
        this.registerGuestUseCase = registerGuestUseCase;
        this.registerAdminUseCase = registerAdminUseCase;
        this.findUsersUseCase = findUsersUseCase;
        this.findUserByLoginUseCase = findUserByLoginUseCase;
    }

    @PostMapping("/register-guest")
    public ResponseEntity<?> registerGuest(
            final @RequestBody RegisterUserRequestDTO request
    ) {
        final var userDomainEntity = UserDomainEntityMapper.toDomainEntity(request);
        registerGuestUseCase.execute(userDomainEntity);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(
            final @RequestBody RegisterUserRequestDTO request
    ) {
        final var userDomainEntity = UserDomainEntityMapper.toDomainEntity(request);
        registerUserUseCase.execute(userDomainEntity);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(
            final @RequestBody RegisterUserRequestDTO request
    ) {
        final var userDomainEntity = UserDomainEntityMapper.toDomainEntity(request);
        registerAdminUseCase.execute(userDomainEntity);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomResponseDTO<List<UserDomainEntity>>()
                        .setData(
                                findUsersUseCase.execute(null)
                        )
        );
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<?> getUserByLogin(
            final @PathVariable String login
    ) {

        final var userDomainEntity = UserDomainEntity
                .builder()
                .login(login)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomResponseDTO<UserDomainEntity>()
                        .setData(
                                findUserByLoginUseCase.execute(userDomainEntity)
                        )
        );
    }
}
