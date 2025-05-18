package com.github.caua.sistema_escolar.repositories;

import com.github.caua.sistema_escolar.model.Boletim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletimRepository extends JpaRepository<Boletim, Long> {
}
