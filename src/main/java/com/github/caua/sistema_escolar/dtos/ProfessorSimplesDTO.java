package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.usuarios.Professor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ProfessorSimplesDTO extends UsuarioDTO {

    public static ProfessorSimplesDTO fromEntityToDto(Professor data) {
        return ProfessorSimplesDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .tipo(data.getTipo())
                .build();
    }
}
