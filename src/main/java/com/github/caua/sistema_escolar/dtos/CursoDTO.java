package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Curso;
import com.github.caua.sistema_escolar.model.Materia;
import com.github.caua.sistema_escolar.model.Turma;
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
    private List<Materia> materias;
    private List<Turma> turmas;

    public static Curso fromDtoToEntity(CursoDTO data) {
        return Curso.builder()
                .nome(data.getNome())
                .turno(data.getTurno())
                .cargaHoraria(data.getCargaHoraria())
                .materias(data.getMaterias())
                .build();
    }

    public static CursoDTO fromEntityToDto(Curso data) {
        return CursoDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .turno(data.getTurno())
                .cargaHoraria(data.getCargaHoraria())
                .materias(data.getMaterias())
                .build();
    }
}
