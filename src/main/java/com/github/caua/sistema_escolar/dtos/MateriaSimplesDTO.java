package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Materia;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MateriaSimplesDTO {
    private Long id;
    private String nome;
    private Integer cargaHoraria;

    public static MateriaSimplesDTO fromEntityToDto(Materia data) {
        return MateriaSimplesDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .cargaHoraria(data.getCargaHoraria())
                .build();
    }
}
