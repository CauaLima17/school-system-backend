package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.usuarios.Professor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
public class ProfessorDTO extends UsuarioDTO {
    private List<MateriaSimplesDTO> materias;

    public static ProfessorDTO fromEntityToDto(Professor data) {
        return ProfessorDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .materias(
                        data.getMaterias()
                        .stream()
                        .map(MateriaSimplesDTO::fromEntityToDto)
                        .toList()
                )
                .tipo(data.getTipo())
                .build();
    }
}
