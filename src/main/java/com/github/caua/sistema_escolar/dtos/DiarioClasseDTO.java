package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.DiarioClasse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DiarioClasseDTO {
    private Long id;
    private ProfessorSimplesDTO professor;
    private TurmaSimplesDTO turma;
    private LocalDateTime data;
    private MateriaSimplesDTO materia;
    private String descricao;

    public static DiarioClasseDTO fromEntityToDto(DiarioClasse data) {
        return DiarioClasseDTO.builder()
                .id(data.getId())
                .professor(ProfessorSimplesDTO.fromEntityToDto(data.getProfessor()))
                .turma(TurmaSimplesDTO.fromEntityToDto(data.getTurma()))
                .data(data.getData())
                .materia(MateriaSimplesDTO.fromEntityToDto(data.getMateria()))
                .descricao(data.getDescricao())
                .build();
    }
}
