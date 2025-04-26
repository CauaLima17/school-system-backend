package com.github.caua.sistema_escolar.repositories;

import com.github.caua.sistema_escolar.model.usuarios.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT s FROM Usuario s WHERE TYPE(s) = Professor AND s.matricula = :matricula")
    Optional<Professor> findByMatricula(@Param("matricula") String matricula);
}
