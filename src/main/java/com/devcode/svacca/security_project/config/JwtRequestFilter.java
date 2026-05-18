package com.devcode.svacca.security_project.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.devcode.svacca.security_project.util.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // validar que la cabecera existe
        var header = request.getHeader("Authorization");
        // validar que el token no es nuel y que empiza por BEARER
        if (header != null && header.startsWith("Bearer ")) {
            // extraer el token
            var token = header.substring(7);
            // si el token es valido ingresa a la condición
            if (jwtUtils.validateToken(token)) {
                // extacción de los claims
                var username = jwtUtils.extractClaim(token, Claims::getSubject);
                var email = jwtUtils.extractClaim(token, (claims) -> claims.get("email", String.class));
                var name = jwtUtils.extractClaim(token, (claims) -> claims.get("name", String.class));
                var hireDate = jwtUtils.extractClaim(token, (claims) -> claims.get("hire_date", String.class));
                // extraccion de los roles
                var roles = jwtUtils.extractClaim(token, (claims) -> {
                    var result = new HashSet<SimpleGrantedAuthority>();
                    var info = claims.get("roles", List.class);
                    for (var item : info) {
                        result.add(new SimpleGrantedAuthority(item.toString()));
                    }
                    return result;                  

                });
                // Se agrega la información al contexto
                var userToken = new UsernamePasswordAuthenticationToken(
                        Map.of(
                                "username", username,
                                "email", email,
                                "name", name,
                                "hire_date", hireDate
                            ),
                        "",
                        roles);

                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
        }
        // Se continua al sigueitne paso
        filterChain.doFilter(request, response);
    }
}
