package br.com.emerlopes.customerauthentication.application.entrypoint.rest;

import br.com.emerlopes.customerauthentication.application.entrypoint.rest.dto.RegisterUserRequestDTO;
import br.com.emerlopes.customerauthentication.application.shared.CustomResponseDTO;
import br.com.emerlopes.customerauthentication.application.mapper.UserDomainEntityMapper;
import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.usecase.*;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserController.class);
    private final Logger logger = Logger.getLogger(UserController.class.getName());
    private final RegisterUserUseCase registerUserUseCase;
    private final RegisterGuestUseCase registerGuestUseCase;
    private final RegisterAdminUseCase registerAdminUseCase;
    private final FindUsersUseCase findUsersUseCase;
    private final FindUserByLoginUseCase findUserByLoginUseCase;

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

        logger.info("Registering guest user");

        final var userDomainEntity = UserDomainEntityMapper.toDomainEntity(request);
        registerGuestUseCase.execute(userDomainEntity);

        logger.info("Guest user registered");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(
            final @RequestBody RegisterUserRequestDTO request
    ) {

        logger.info("Registering user");

        final var userDomainEntity = UserDomainEntityMapper.toDomainEntity(request);
        registerUserUseCase.execute(userDomainEntity);

        logger.info("User registered");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(
            final @RequestBody RegisterUserRequestDTO request
    ) {

        logger.info("Registering admin user");

        final var userDomainEntity = UserDomainEntityMapper.toDomainEntity(request);
        registerAdminUseCase.execute(userDomainEntity);

        logger.info("Admin user registered");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {

        logger.info("Finding users");

        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomResponseDTO<List<UserDomainEntity>>()
                        .setData(
                                findUsersUseCase.execute(null)
                        )
        );
    }

    @GetMapping("/{login}")
    public ResponseEntity<?> getUserByLogin(
            final @PathVariable String login
    ) {

        logger.info("Finding user by login");

        final var userDomainEntity = UserDomainEntity
                .builder()
                .login(login)
                .build();

        logger.info("User found");

        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomResponseDTO<UserDomainEntity>()
                        .setData(
                                findUserByLoginUseCase.execute(userDomainEntity)
                        )
        );
    }
}
