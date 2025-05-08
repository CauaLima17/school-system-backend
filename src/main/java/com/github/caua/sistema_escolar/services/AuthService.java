package com.github.caua.sistema_escolar.services;

import com.github.caua.sistema_escolar.dtos.UsuarioDTO;
import com.github.caua.sistema_escolar.model.usuarios.DetalhesUsuario;
import com.github.caua.sistema_escolar.model.usuarios.Usuario;
import com.github.caua.sistema_escolar.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByMatricula(matricula)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        DetalhesUsuario detalhesUsuario = DetalhesUsuario.fromUsuarioToDetalhesUsuario(usuario);
        return detalhesUsuario;
    }

    //TODO - Terminar a lógica de login e testar
    public String login(UsuarioDTO usuario) {
        return "";
    }
}
