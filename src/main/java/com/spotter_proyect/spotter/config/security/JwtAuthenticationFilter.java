
package com.spotter_proyect.spotter.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor // Lombok crea el constructor con los argumentos 'final' automáticamente
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService; // Tu clase de utilidad
    private final UserDetailsService userDetailsService; // Interfaz de Spring para buscar usuarios

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Obtener el header de autorización de la petición HTTP
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // 2. Comprobar si el header es válido (debe empezar por "Bearer ")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Si no hay token, pasa al siguiente filtro (y rebotará si es ruta privada)
            return;
        }

        // 3. Extraer el token (quitar la palabra "Bearer " del principio)
        jwt = authHeader.substring(7);

        // 4. Extraer el email del usuario del token
        userEmail = jwtService.extractUsername(jwt);

        // 5. Si hay email y el usuario NO está autenticado todavía en el contexto de seguridad...
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Cargar los detalles del usuario desde la base de datos
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // 6. Verificar si el token es válido
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // Crear el objeto de autenticación (el "pase" oficial de Spring)
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 7. "Fichar" al usuario en el sistema de seguridad para esta petición
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
