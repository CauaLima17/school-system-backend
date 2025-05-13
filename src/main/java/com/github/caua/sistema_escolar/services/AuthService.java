package com.github.caua.sistema_escolar.services;

import com.github.caua.sistema_escolar.config.security.jwt.JwtTokenProvider;
import com.github.caua.sistema_escolar.dtos.UsuarioDTO;
import com.github.caua.sistema_escolar.model.usuarios.DetalhesUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public UsuarioDTO login(UsuarioDTO usuario) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword =
                    new UsernamePasswordAuthenticationToken(usuario.getMatricula(), usuario.getSenha());
            Authentication auth = authenticationManager.authenticate(usernamePassword);
            DetalhesUsuario detalhesUsuario = (DetalhesUsuario) auth.getPrincipal();

            return UsuarioDTO.builder()
                    .id(detalhesUsuario.getId())
                    .nome(detalhesUsuario.getNome())
                    .email(detalhesUsuario.getEmail())
                    .matricula(detalhesUsuario.getMatricula())
                    .tipo(detalhesUsuario.getTipo())
                    .token(jwtTokenProvider.generateToken(detalhesUsuario))
                    .build();

        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas. Por favor, tente " +
                    "novamente");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro de validação desconhecido");
        }
    }
}
