package com.github.caua.sistema_escolar.repositories;

import com.github.caua.sistema_escolar.model.usuarios.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("SELECT s FROM Usuario s WHERE TYPE(s) = Aluno AND s.matricula = :matricula")
    Optional<Aluno> findByMatricula(@Param("matricula") String matricula);

    @Query("SELECT s FROM Usuario s WHERE TYPE(s) = Aluno AND s.email = :email")
    Optional<Aluno> findByEmail(@Param("email") String email);
}
