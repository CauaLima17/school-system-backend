package com.github.caua.sistema_escolar.repositories;

import com.github.caua.sistema_escolar.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("SELECT c FROM Curso c WHERE c.nome = :nome")
    Optional<Curso> findByNome(@Param("nome") String nome);
}
