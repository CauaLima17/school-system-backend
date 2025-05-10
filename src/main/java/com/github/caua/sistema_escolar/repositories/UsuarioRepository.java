package com.github.caua.sistema_escolar.repositories;

import com.github.caua.sistema_escolar.model.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT s FROM Usuario s WHERE s.matricula = :matricula")
    Optional<Usuario> findByMatricula(@Param("matricula") String matricula);
}
