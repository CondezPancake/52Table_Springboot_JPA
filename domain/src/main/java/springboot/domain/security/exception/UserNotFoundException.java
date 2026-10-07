package springboot.domain.security.exception;

public class UserNotFoundException extends SecurityDomainException {
    public UserNotFoundException() {
        super("Usuario no encontrado");
    }
}
