package com.github.caua.sistema_escolar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Boletim extends Entidade {
    private String bimestre;
    private Double nota;
    private Long quantidadeFaltas;
    @ManyToOne
    private Materia materia;
}
