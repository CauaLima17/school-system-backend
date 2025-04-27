package com.github.caua.sistema_escolar.model.usuarios;

import com.github.caua.sistema_escolar.model.Materia;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Representa os professores do sistema
 *
 * @author Cauã
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("Professor")
@SuperBuilder
public class Professor extends Usuario {
    @ManyToMany(mappedBy = "professores")
    private List<Materia> materias;
}
