package com.github.caua.sistema_escolar.repositories;

import com.github.caua.sistema_escolar.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    @Query("SELECT m FROM Materia m WHERE m.nome = :nome")
    Optional<Materia> findByNome(@Param("nome") String nome);
}
