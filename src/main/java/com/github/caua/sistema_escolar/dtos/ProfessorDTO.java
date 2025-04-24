package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Materia;
import com.github.caua.sistema_escolar.model.usuarios.Aluno;
import com.github.caua.sistema_escolar.model.usuarios.Professor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class ProfessorDTO extends UsuarioDTO {
    private List<Materia> materias;

    public static Professor fromDtoToEntity(ProfessorDTO data) {
        return Professor.builder()
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .materias(data.getMaterias())
                .senha(data.getSenha())
                .build();
    }

    public static ProfessorDTO fromEntityToDto(Professor data) {
        return ProfessorDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .materias(data.getMaterias())
                .build();
    }
}
