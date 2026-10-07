package springboot.infrastructure.security.service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import springboot.domain.security.model.AuthRole;
import springboot.domain.security.port.TokenService;

@Service
public class JwtTokenService implements TokenService {
    private final SecretKey key;
    private final Duration accessTokenLifetime;
    private final SecureRandom secureRandom = new SecureRandom();

    public JwtTokenService(@Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-expiration}") long accessTokenExpirationMillis) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenLifetime = Duration.ofMillis(accessTokenExpirationMillis);
    }

    @Override
    public String generateAccessToken(UUID userId, String email, Set<AuthRole> roles) {
        Instant now = Instant.now();
        List<String> authorities = roles.stream().map(AuthRole::authority).sorted().toList();
        return Jwts.builder()
                .subject(userId.toString())
                .claim("email", email)
                .claim("roles", authorities)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(accessTokenLifetime)))
                .signWith(key)
                .compact();
    }

    @Override
    public String generateRefreshToken() {
        byte[] bytes = new byte[48];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    @Override
    public UUID validateAndGetUserId(String token) {
        return UUID.fromString(parseClaims(token).getSubject());
    }

    @Override
    public Set<String> extractAuthorities(String token) {
        List<?> roles = parseClaims(token).get("roles", List.class);
        if (roles == null) {
            return Set.of();
        }
        return roles.stream().map(String::valueOf).collect(Collectors.toUnmodifiableSet());
    }

    private Claims parseClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}
