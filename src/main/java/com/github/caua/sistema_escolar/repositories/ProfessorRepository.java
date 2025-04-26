package com.github.caua.sistema_escolar.repositories;

import com.github.caua.sistema_escolar.model.usuarios.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
