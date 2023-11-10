package agroscience.fields.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;

import java.io.IOException;

@Component
public class JwtFilter {
    @Value("${JWT.SECRET.KEY}")
    private String secretKey;

    public String extractJwtFromRequest(HttpServletRequest request) {
        String jwt = null;
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            jwt = header.substring(7); // Убираем "Bearer " из заголовка
        }
        return jwt;
    }

    public Role extractRoleFromJwt(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes()) // Здесь укажите ваш ключ для декодирования токена
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return Role.valueOf(claims.get("role", String.class)); // Предполагается, что роль хранится в поле "role"
    }

    public Long extractOrganizationIdFromJwt(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes()) // Здесь укажите ваш ключ для декодирования токена
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims.get("org", Long.class); // Предполагается, что organizationId хранится в поле "organizationId"
    }

}
