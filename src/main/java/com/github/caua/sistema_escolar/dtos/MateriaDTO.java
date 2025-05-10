package com.github.caua.sistema_escolar.dtos;

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
    private List<ProfessorSimplesDTO> professores;
    private Integer cargaHoraria;

    public static MateriaDTO fromEntityToDto(Materia data) {
        return MateriaDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .professores(
                        data.getProfessores()
                        .stream()
                        .map(ProfessorSimplesDTO::fromEntityToDto)
                        .toList()
                )
                .cargaHoraria(data.getCargaHoraria())
                .build();
    }
}
