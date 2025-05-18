package com.github.caua.sistema_escolar.repositories;

import com.github.caua.sistema_escolar.model.DiarioClasse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiarioClasseRepository extends JpaRepository<DiarioClasse, Long> {
}
