package springboot.infrastructure.security.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import springboot.application.security.command.ChangePasswordCommand;
import springboot.application.security.command.LoginCommand;
import springboot.application.security.command.RegisterUserCommand;
import springboot.application.security.dto.AuthTokensResponse;
import springboot.application.security.dto.AuthUserResponse;
import springboot.application.security.usecase.ChangePasswordUseCase;
import springboot.application.security.usecase.GetCurrentUserUseCase;
import springboot.application.security.usecase.LoginUseCase;
import springboot.application.security.usecase.LogoutUseCase;
import springboot.application.security.usecase.RefreshAccessTokenUseCase;
import springboot.application.security.usecase.RegisterUserUseCase;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final RegisterUserUseCase registerUser;
    private final LoginUseCase login;
    private final RefreshAccessTokenUseCase refreshAccessToken;
    private final LogoutUseCase logout;
    private final GetCurrentUserUseCase getCurrentUser;
    private final ChangePasswordUseCase changePassword;

    public AuthController(RegisterUserUseCase registerUser, LoginUseCase login,
            RefreshAccessTokenUseCase refreshAccessToken, LogoutUseCase logout,
            GetCurrentUserUseCase getCurrentUser, ChangePasswordUseCase changePassword) {
        this.registerUser = registerUser;
        this.login = login;
        this.refreshAccessToken = refreshAccessToken;
        this.logout = logout;
        this.getCurrentUser = getCurrentUser;
        this.changePassword = changePassword;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUserResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthUserResponse response = registerUser.execute(
                new RegisterUserCommand(request.email(), request.password()));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public AuthTokensResponse login(@Valid @RequestBody LoginRequest request) {
        return login.execute(new LoginCommand(request.email(), request.password()));
    }

    @PostMapping("/refresh")
    public AuthTokensResponse refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return refreshAccessToken.execute(request.refreshToken());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody RefreshTokenRequest request,
            Authentication authentication) {
        logout.execute(principal(authentication), request.refreshToken());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public AuthUserResponse me(Authentication authentication) {
        return getCurrentUser.execute(principal(authentication));
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest request,
            Authentication authentication) {
        changePassword.execute(new ChangePasswordCommand(principal(authentication),
                request.currentPassword(), request.newPassword()));
        return ResponseEntity.noContent().build();
    }

    private UUID principal(Authentication authentication) {
        return (UUID) authentication.getPrincipal();
    }

    public record RegisterRequest(
            @NotBlank @Email String email,
            @NotBlank @Size(min = 8, max = 72) String password) { }

    public record LoginRequest(
            @NotBlank @Email String email,
            @NotBlank String password) { }

    public record RefreshTokenRequest(@NotBlank String refreshToken) { }

    public record ChangePasswordRequest(
            @NotBlank String currentPassword,
            @NotBlank @Size(min = 8, max = 72) String newPassword) { }
}
