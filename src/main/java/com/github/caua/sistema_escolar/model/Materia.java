package com.github.caua.sistema_escolar.model;

import com.github.caua.sistema_escolar.model.usuarios.Professor;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Materia extends Entidade {
    private String nome;
    @ManyToOne
    private Professor professor;
    private Integer cargaHoraria;
}
