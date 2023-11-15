package agroscience.fields.security;

import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Map;

@Component
public class JwtFilter {
    @Value("${JWT.SECRET.KEY}")
    private String secretKey;

    public Map<String, Object> extractJwtFromRequest(HttpServletRequest request) throws ParseException {
        String jwt = null;
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            jwt = header.substring(7); // Убираем "Bearer " из заголовка
        }

        assert jwt != null;
        SignedJWT decodedJWT = SignedJWT.parse(jwt);
        var claims = decodedJWT.getJWTClaimsSet();

        return claims.getClaims();
    }

    public Role extractRoleFromJwt(Map<String, Object> claims) {
        return Role.valueOf((String)claims.get("role")); // Предполагается, что роль хранится в поле "role"
    }

    public Long extractOrganizationIdFromJwt(Map<String, Object> claims) {
        return (Long)claims.get("org"); // Предполагается, что organizationId хранится в поле "organizationId"
    }

}
