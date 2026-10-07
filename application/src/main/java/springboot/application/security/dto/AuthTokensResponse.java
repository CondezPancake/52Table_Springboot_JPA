package springboot.application.security.dto;

public record AuthTokensResponse(String accessToken, String refreshToken,
        String tokenType, long expiresIn) {
    public static AuthTokensResponse bearer(String accessToken, String refreshToken,
            long expiresInSeconds) {
        return new AuthTokensResponse(accessToken, refreshToken, "Bearer", expiresInSeconds);
    }
}
