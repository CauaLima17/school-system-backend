package com.github.caua.sistema_escolar.repositories;

import com.github.caua.sistema_escolar.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
