package springboot.infrastructure.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springboot.domain.security.port.PasswordService;

@Service
public class BCryptPasswordService implements PasswordService {
    private final PasswordEncoder encoder;

    public BCryptPasswordService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String hash(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    @Override
    public boolean matches(String plainPassword, String passwordHash) {
        return encoder.matches(plainPassword, passwordHash);
    }
}
