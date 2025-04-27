package com.github.caua.sistema_escolar.model.usuarios;

import com.github.caua.sistema_escolar.model.Turma;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Representa os alunos do sistema
 *
 * @author Cauã
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("Aluno")
@SuperBuilder
public class Aluno extends Usuario {
    @ManyToOne
    private Turma turma;
}
