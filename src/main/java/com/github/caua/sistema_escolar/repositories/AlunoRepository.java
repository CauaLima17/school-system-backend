package com.github.caua.sistema_escolar.repositories;

import com.github.caua.sistema_escolar.model.usuarios.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
