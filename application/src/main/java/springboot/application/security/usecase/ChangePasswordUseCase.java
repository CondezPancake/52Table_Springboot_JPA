package springboot.application.security.usecase;

import springboot.application.security.command.ChangePasswordCommand;
import springboot.domain.security.exception.InvalidCredentialsException;
import springboot.domain.security.exception.UserNotFoundException;
import springboot.domain.security.model.AuthUser;
import springboot.domain.security.port.AuthUserRepository;
import springboot.domain.security.port.PasswordService;

public class ChangePasswordUseCase {
    private final AuthUserRepository users;
    private final PasswordService passwords;

    public ChangePasswordUseCase(AuthUserRepository users, PasswordService passwords) {
        this.users = users;
        this.passwords = passwords;
    }

    public void execute(ChangePasswordCommand command) {
        AuthUser user = users.findById(command.userId())
                .orElseThrow(UserNotFoundException::new);
        if (!passwords.matches(command.currentPassword(), user.passwordHash())) {
            throw new InvalidCredentialsException("La contraseña actual es incorrecta");
        }
        user.changePassword(passwords.hash(command.newPassword()));
        users.save(user);
    }
}
