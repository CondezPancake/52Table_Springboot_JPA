package springboot.infrastructure.security.web;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import springboot.application.security.command.AssignRoleCommand;
import springboot.application.security.dto.AuthUserResponse;
import springboot.application.security.usecase.AssignRoleUseCase;

@RestController
@RequestMapping("/api/users")
public class UserRoleController {
    private final AssignRoleUseCase assignRole;

    public UserRoleController(AssignRoleUseCase assignRole) {
        this.assignRole = assignRole;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{userId}/roles")
    public AuthUserResponse assignRole(@PathVariable UUID userId,
            @Valid @RequestBody AssignRoleRequest request) {
        return assignRole.execute(new AssignRoleCommand(userId, request.role()));
    }

    public record AssignRoleRequest(@NotBlank String role) { }
}
