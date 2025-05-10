package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Curso;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoSimplesDTO {
    private Long id;
    private String nome;
    private String turno;
    private Long cargaHoraria;

    public static CursoSimplesDTO fromEntityToDto(Curso data) {
        return CursoSimplesDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .turno(data.getTurno())
                .cargaHoraria(data.getCargaHoraria())
                .build();
    }
}
