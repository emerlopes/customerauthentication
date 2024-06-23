package br.com.emerlopes.customerauthentication.application.entrypoint.rest;

import br.com.emerlopes.customerauthentication.application.entrypoint.rest.shared.CustomResponseDTO;
import br.com.emerlopes.customerauthentication.domain.entity.UserDomainEntity;
import br.com.emerlopes.customerauthentication.domain.usecase.FindUsersUseCase;
import br.com.emerlopes.customerauthentication.domain.usecase.RegisterUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    final RegisterUserUseCase registerUserUseCase;
    final FindUsersUseCase findUsersUseCase;

    public UserController(
            final RegisterUserUseCase registerUserUseCase,
            final FindUsersUseCase findUsersUseCase
    ) {
        this.registerUserUseCase = registerUserUseCase;
        this.findUsersUseCase = findUsersUseCase;
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
}
