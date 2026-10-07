package springboot.application.security.mapper;

import java.util.stream.Collectors;

import springboot.application.security.dto.AuthUserResponse;
import springboot.domain.security.model.AuthRole;
import springboot.domain.security.model.AuthUser;

public final class AuthUserMapper {
    private AuthUserMapper() { }

    public static AuthUserResponse toResponse(AuthUser user) {
        return new AuthUserResponse(
                user.id(),
                user.email(),
                user.roles().stream()
                        .map(AuthRole::authority)
                        .collect(Collectors.toUnmodifiableSet()),
                user.status());
    }
}
