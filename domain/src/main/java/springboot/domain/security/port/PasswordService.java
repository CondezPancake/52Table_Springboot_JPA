package springboot.domain.security.port;

public interface PasswordService {
    String hash(String plainPassword);
    boolean matches(String plainPassword, String passwordHash);
}
