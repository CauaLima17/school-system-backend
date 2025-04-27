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
    private String nome;
    @ManyToMany
    @JoinTable(
            name = "materia_professores",
            joinColumns = @JoinColumn(name = "materia_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores;
    private Integer cargaHoraria;

    @ManyToMany(mappedBy = "materias")
    private List<Curso> curso;
}
