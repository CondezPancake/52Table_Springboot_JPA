package springboot.application.security.dto;

import java.util.Set;
import java.util.UUID;

import springboot.domain.security.model.UserStatus;

public record AuthUserResponse(UUID id, String email, Set<String> roles, UserStatus status) { }
