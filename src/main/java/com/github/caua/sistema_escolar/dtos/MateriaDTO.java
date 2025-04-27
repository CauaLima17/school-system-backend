package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Curso;
import com.github.caua.sistema_escolar.model.Materia;
import com.github.caua.sistema_escolar.model.usuarios.Professor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MateriaDTO {
    private Long id;
    private String nome;
    private List<Professor> professores;
    private Integer cargaHoraria;

    public static Materia fromDtoToEntity(MateriaDTO data) {
        return Materia.builder()
                .nome(data.getNome())
                .professores(data.getProfessores())
                .cargaHoraria(data.getCargaHoraria())
                .build();
    }

    public static MateriaDTO fromEntityToDto(Materia data) {
        return MateriaDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .professores(data.getProfessores())
                .cargaHoraria(data.getCargaHoraria())
                .build();
    }
}
