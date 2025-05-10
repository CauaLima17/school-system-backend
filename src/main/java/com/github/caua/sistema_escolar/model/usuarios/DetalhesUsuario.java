package com.github.caua.sistema_escolar.model.usuarios;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class DetalhesUsuario extends Usuario implements UserDetails {

    public static DetalhesUsuario fromUsuarioToDetalhesUsuario(Usuario usuarioAuth) {
        return DetalhesUsuario.builder()
                .id(usuarioAuth.getId())
                .nome(usuarioAuth.getNome())
                .matricula(usuarioAuth.getMatricula())
                .email(usuarioAuth.getEmail())
                .senha(usuarioAuth.getSenha())
                .tipo(usuarioAuth.getTipo())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (super.getTipo().equals("Admin")) return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
        );

        else if (super.getTipo().equals("Professor")) return List.of(
                new SimpleGrantedAuthority("ROLE_PROFESSOR"),
                new SimpleGrantedAuthority("ROLE_USER")
        );

        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return super.getSenha();
    }

    @Override
    public String getUsername() {
        return super.getMatricula();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
