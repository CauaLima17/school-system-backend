package com.github.caua.sistema_escolar.model.usuarios;

import com.github.caua.sistema_escolar.model.Boletim;
import com.github.caua.sistema_escolar.model.Turma;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    @OneToMany
    private List<Boletim> boletims;
    @ManyToOne
    private Turma turma;
}
