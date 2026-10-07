package springboot.application.security.usecase;

import java.util.UUID;

import springboot.application.security.dto.AuthUserResponse;
import springboot.application.security.mapper.AuthUserMapper;
import springboot.domain.security.exception.UserNotFoundException;
import springboot.domain.security.port.AuthUserRepository;

public class GetCurrentUserUseCase {
    private final AuthUserRepository users;

    public GetCurrentUserUseCase(AuthUserRepository users) {
        this.users = users;
    }

    public AuthUserResponse execute(UUID userId) {
        return users.findById(userId)
                .map(AuthUserMapper::toResponse)
                .orElseThrow(UserNotFoundException::new);
    }
}
