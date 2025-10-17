package D.S.Store.B0.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String SECRET = "change-me-to-a-very-long-secret-key-256-bits-minimum-1234567890";
    private static final long EXPIRATION_MS = 24 * 60 * 60 * 1000; // 24h

    private final Key signingKey = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username, String cargo) {
        return Jwts.builder()
            .setSubject(username)
            .claim("cargo", cargo)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return parseClaims(token).getBody().getSubject();
    }

    public String extractCargo(String token) {
        Object value = parseClaims(token).getBody().get("cargo");
        return value != null ? value.toString() : null;
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
    }
}
