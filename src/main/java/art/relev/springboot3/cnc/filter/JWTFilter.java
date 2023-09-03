package art.relev.springboot3.cnc.filter;

import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.model.User;
import art.relev.springboot3.cnc.service.JWTService;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final String TOKEN = "TOKEN";
    private final JWTService jwtService;
    private final StringRedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        User user = User.builder().build();
        String token = request.getHeader(TOKEN);
        SignedJWT jwt = jwtService.parse(token);
        if (jwt != null && jwtService.get(jwt, "userResource") instanceof Map map && map.get("id") instanceof Long id && map.get("typeName") instanceof String typeName && typeName.equals(redisTemplate.opsForValue().get("TOKEN_" + token))) {
            Resource resource = Resource.builder().id(id).typeName(typeName).build();
            user.setResource(resource);
        } else {
            response.setHeader(TOKEN, "");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, token, null);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
