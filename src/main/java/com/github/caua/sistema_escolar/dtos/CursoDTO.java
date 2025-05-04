package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Curso;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CursoDTO {
    private Long id;
    private String nome;
    private String turno;
    private Long cargaHoraria;
    private List<MateriaSimplesDTO> materias;

    public static CursoDTO fromEntityToDto(Curso data) {
        return CursoDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .turno(data.getTurno())
                .cargaHoraria(data.getCargaHoraria())
                .materias(data.getMaterias()
                        .stream()
                        .map(MateriaSimplesDTO::fromEntityToDto)
                        .toList())
                .build();
    }
}
