package springboot.application.security.usecase;

import springboot.application.security.command.AssignRoleCommand;
import springboot.application.security.dto.AuthUserResponse;
import springboot.application.security.mapper.AuthUserMapper;
import springboot.domain.security.exception.SecurityDomainException;
import springboot.domain.security.exception.UserNotFoundException;
import springboot.domain.security.model.AuthRole;
import springboot.domain.security.model.AuthUser;
import springboot.domain.security.port.AuthRoleRepository;
import springboot.domain.security.port.AuthUserRepository;

public class AssignRoleUseCase {
    private final AuthUserRepository users;
    private final AuthRoleRepository roles;

    public AssignRoleUseCase(AuthUserRepository users, AuthRoleRepository roles) {
        this.users = users;
        this.roles = roles;
    }

    public AuthUserResponse execute(AssignRoleCommand command) {
        AuthUser user = users.findById(command.userId()).orElseThrow(UserNotFoundException::new);
        String requestedRole = command.role().trim();
        AuthRole role = roles.findByName(requestedRole)
                .or(() -> roles.findByAuthority(requestedRole))
                .orElseThrow(() -> new SecurityDomainException("Rol no encontrado: " + requestedRole));
        user.assignRole(role);
        return AuthUserMapper.toResponse(users.save(user));
    }
}
