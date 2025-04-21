package com.github.caua.sistema_escolar.service;

import com.github.caua.sistema_escolar.model.usuarios.DetalhesUsuario;
import com.github.caua.sistema_escolar.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DetalhesUsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public DetalhesUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByMatricula(login)
                .map(DetalhesUsuario::fromUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com login: " + login));
    }
}
