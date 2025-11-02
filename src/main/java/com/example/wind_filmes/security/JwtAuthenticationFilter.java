package com.example.wind_filmes.security;

import com.example.wind_filmes.entity.User;
import com.example.wind_filmes.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(header) && header.toLowerCase().startsWith("bearer ")) {
            String token = header.substring(7); // "Bearer ".length() == 7
            try {
                String email = jwtService.getSubject(token);
                User user = userRepository.findByEmail(email).orElse(null);

                // só autentica se ainda não houver auth no contexto e o usuário estiver ativo
                if (SecurityContextHolder.getContext().getAuthentication() == null
                        && user != null && user.isActive()) {

                    var auth = new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception ignored) {
                // token inválido/expirado -> segue sem autenticar (rotas protegidas darão 401/403)
                // para depurar, você pode logar aqui se quiser
            }
        }

        chain.doFilter(request, response);
    }
}
