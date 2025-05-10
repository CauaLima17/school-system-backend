package com.github.caua.sistema_escolar.repositories;

import com.github.caua.sistema_escolar.model.usuarios.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("SELECT s FROM Usuario s WHERE TYPE(s) = Admin AND s.matricula = :matricula")
    Optional<Admin> findByMatricula(@Param("matricula") String matricula);

    @Query("SELECT s FROM Usuario s WHERE TYPE(s) = Admin AND s.email = :email")
    Optional<Admin> findByEmail(@Param("email") String email);
}
