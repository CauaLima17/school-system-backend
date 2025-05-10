package com.github.caua.sistema_escolar.services;

import com.github.caua.sistema_escolar.model.usuarios.DetalhesUsuario;
import com.github.caua.sistema_escolar.model.usuarios.Usuario;
import com.github.caua.sistema_escolar.repositories.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class DetalhesUsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public DetalhesUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByMatricula(matricula)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return DetalhesUsuario.fromUsuarioToDetalhesUsuario(usuario);
    }
}
