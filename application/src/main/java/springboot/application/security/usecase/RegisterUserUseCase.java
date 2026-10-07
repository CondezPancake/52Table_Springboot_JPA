package springboot.application.security.usecase;

import springboot.application.security.command.RegisterUserCommand;
import springboot.application.security.dto.AuthUserResponse;
import springboot.application.security.mapper.AuthUserMapper;
import springboot.domain.security.exception.SecurityDomainException;
import springboot.domain.security.model.AuthRole;
import springboot.domain.security.model.AuthUser;
import springboot.domain.security.port.AuthRoleRepository;
import springboot.domain.security.port.AuthUserRepository;
import springboot.domain.security.port.PasswordService;

public class RegisterUserUseCase {
    private final AuthUserRepository users;
    private final AuthRoleRepository roles;
    private final PasswordService passwords;

    public RegisterUserUseCase(AuthUserRepository users, AuthRoleRepository roles,
            PasswordService passwords) {
        this.users = users;
        this.roles = roles;
        this.passwords = passwords;
    }

    public AuthUserResponse execute(RegisterUserCommand command) {
        String email = command.email() == null ? null : command.email().trim().toLowerCase();
        if (email != null && users.existsByEmail(email)) {
            throw new SecurityDomainException("Ya existe un usuario con ese email");
        }
        AuthUser user = AuthUser.register(email, passwords.hash(command.password()));
        AuthRole defaultRole = roles.findByAuthority("ROLE_USER")
                .orElseThrow(() -> new SecurityDomainException("El rol ROLE_USER no está configurado"));
        user.assignRole(defaultRole);
        return AuthUserMapper.toResponse(users.save(user));
    }
}
