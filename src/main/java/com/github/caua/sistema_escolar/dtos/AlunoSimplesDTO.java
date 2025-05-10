package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.usuarios.Aluno;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class AlunoSimplesDTO extends UsuarioDTO {

    public static AlunoSimplesDTO fromEntityToDto(Aluno data) {
        return AlunoSimplesDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .tipo(data.getTipo())
                .build();
    }
}
