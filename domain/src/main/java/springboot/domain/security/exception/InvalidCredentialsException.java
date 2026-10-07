package springboot.domain.security.exception;

public class InvalidCredentialsException extends SecurityDomainException {
    public InvalidCredentialsException() {
        super("Credenciales inválidas");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
