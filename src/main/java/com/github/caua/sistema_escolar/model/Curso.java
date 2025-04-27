package com.github.caua.sistema_escolar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Curso extends Entidade {
    @Column(nullable = false, unique = true)
    private String nome;
    private String turno;
    private Long cargaHoraria;
    @ManyToMany
    @JoinTable(
            name = "curso_materias",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    private List<Materia> materias;
    @OneToMany(mappedBy = "curso")
    private List<Turma> turmas;
}
