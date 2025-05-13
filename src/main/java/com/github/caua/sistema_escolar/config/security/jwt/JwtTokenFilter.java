package com.github.caua.sistema_escolar.config.security.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.caua.sistema_escolar.model.usuarios.DetalhesUsuario;
import com.github.caua.sistema_escolar.model.usuarios.Usuario;
import com.github.caua.sistema_escolar.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, UsuarioRepository usuarioRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);

        if (token != null) {
            try {
                String login = jwtTokenProvider.validateToken(token);
                Usuario data = usuarioRepository
                        .findByMatricula(login)
                        .orElseThrow(() -> new RuntimeException("Erro ao validar token: usuário não encontrado"));

                UserDetails usuario = DetalhesUsuario.fromUsuarioToDetalhesUsuario(data);

                Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (RuntimeException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                logger.warn(e.getMessage());
                response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
                return;
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                logger.warn(e.getMessage());
                response.getWriter().write("{\"error\": \"Erro desconhecido ao iniciar sessão\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;

        return authHeader.replace("Bearer ", "");
    }
}
