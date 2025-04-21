package com.github.caua.sistema_escolar.model.usuarios;

import com.github.caua.sistema_escolar.model.Materia;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("Professor")
@SuperBuilder
public class Professor extends Usuario {
    @OneToMany(mappedBy = "professor")
    private List<Materia> materias;
}
