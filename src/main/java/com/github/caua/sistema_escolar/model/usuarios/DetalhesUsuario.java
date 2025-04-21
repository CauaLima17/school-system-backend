package com.github.caua.sistema_escolar.model.usuarios;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalhesUsuario implements UserDetails {
    private Long id;
    private String nome;
    private String email;
    private String matricula;

    @JsonIgnore
    private String senha;
    private Collection<SimpleGrantedAuthority> authorities;

    public static DetalhesUsuario fromUsuario(Usuario usuarioAuth) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        return DetalhesUsuario.builder()
                .id(usuarioAuth.getId())
                .nome(usuarioAuth.getNome())
                .matricula(usuarioAuth.getMatricula())
                .senha(usuarioAuth.getSenha())
                .authorities(authorities)
                .build();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
