package com.github.caua.sistema_escolar.model;

import com.github.caua.sistema_escolar.model.usuarios.Professor;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DiarioClasse extends Entidade {
    @ManyToOne
    private Professor professor;
    @ManyToOne
    private Turma turma;
    private LocalDateTime data;
    @ManyToOne
    private Materia materia;
    private String descricao;
}
