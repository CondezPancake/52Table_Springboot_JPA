package springboot.application.security.command;

public record RegisterUserCommand(String email, String password) { }
