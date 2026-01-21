package com.example.prestamos.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class TokenAuthFilter extends OncePerRequestFilter {

    private static final String CLIENTE_TOKEN = "cliente-token";
    private static final String GESTOR_TOKEN  = "gestor-token";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            Authentication authentication = switch (token) {
                case CLIENTE_TOKEN -> buildAuth("CLIENTE");
                case GESTOR_TOKEN  -> buildAuth("GESTOR");
                default -> null;
            };

            if (authentication != null) {
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private Authentication buildAuth(String role) {
        return new UsernamePasswordAuthenticationToken(
                "static-user",
                null,
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }
}
