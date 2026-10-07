package springboot.application.security.command;

import java.util.UUID;

public record AssignRoleCommand(UUID userId, String role) { }
