package agroscience.fields.security;

import agroscience.fields.exceptions.AuthException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthoriseService {
    private final JwtFilter filter;

    /**
     * Проверяет корректность jwt, осуществляет авторизацию, отдаёт id организации
     */
    public Long doFilter(HttpServletRequest request, List<Role> roles){
        Role role = null;
        String jwt = null;
        Long orgId = null;
        try {
            jwt = filter.extractJwtFromRequest(request);
        }catch (Exception e){
            throw new AuthException("Невалидый jwt");
        }

        try {
            role = filter.extractRoleFromJwt(jwt);
        }catch (Exception e){
            throw new AuthException("Невалидная роль");
        }

        if(!roles.contains(role)){
            throw new AuthException("Отказано в доступе, нужна одна из ролей: " + roles);
        }

        try {
            orgId = filter.extractOrganizationIdFromJwt(jwt);
        }catch (Exception e){
            throw new AuthException("Невалидное id организации");
        }
        return orgId;
    }
}
