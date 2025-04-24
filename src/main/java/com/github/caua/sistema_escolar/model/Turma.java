package com.github.caua.sistema_escolar.model;

import com.github.caua.sistema_escolar.model.usuarios.Aluno;
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
public class Turma extends Entidade {
    private String anoPeriodo;

    @OneToMany(mappedBy = "turma")
    private List<Aluno> alunos;

    @ManyToOne
    private Curso curso;
}
