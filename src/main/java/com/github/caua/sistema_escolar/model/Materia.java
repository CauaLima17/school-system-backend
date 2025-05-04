package com.github.caua.sistema_escolar.model;

import com.github.caua.sistema_escolar.model.usuarios.Professor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Materia extends Entidade {
    @Column(unique = true, nullable = false)
    private String nome;
    @ManyToMany(mappedBy = "materias")
    private List<Professor> professores;
    @Column(nullable = false)
    private Integer cargaHoraria;
}
