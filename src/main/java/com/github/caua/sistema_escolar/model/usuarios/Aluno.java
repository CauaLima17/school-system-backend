package com.github.caua.sistema_escolar.model.usuarios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("Aluno")
@SuperBuilder
public class Aluno extends Usuario {
    private String curso;
}
